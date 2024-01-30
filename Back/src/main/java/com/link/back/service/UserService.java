package com.link.back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.link.back.dto.JwtToken;
import com.link.back.dto.LoginRequest;
import com.link.back.dto.request.AdditionalUserInfoRequest;
import com.link.back.dto.request.UserPasswordResetRequest;
import com.link.back.dto.UserSignUpDto;
import com.link.back.dto.request.UserUpdateInfoRequest;
import com.link.back.dto.response.UserInfoResponsse;
import com.link.back.entity.User;
import com.link.back.entity.UserImage;
import com.link.back.entity.UserSkill;
import com.link.back.repository.RefreshTokenRepository;
import com.link.back.repository.SkillRepository;
import com.link.back.repository.UserImageRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserSkillRepository;
import com.link.back.security.JwtTokenProvider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	private final UserImageRepository userImageRepository;
	private final UserSkillRepository userSkillRepository;
	private final SkillRepository skillRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender javaMailSender;

	public String signup(UserSignUpDto userSignUpDto) throws Exception {

		if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
			throw new Exception("이미 존재하는 이메일입니다.");
		}

		User user = User.builder()
			.email(userSignUpDto.getEmail())
			.password(passwordEncoder.encode(userSignUpDto.getPassword()))
			.name(userSignUpDto.getName())
			.gender(userSignUpDto.isGender())
			.birth(userSignUpDto.getBirth())
			.phoneNumber(userSignUpDto.getPhoneNumber())
			.rating(userSignUpDto.getRating())
			.build();

		userRepository.save(user);
		return "회원가입 성공";

	}

	public JwtToken login(LoginRequest loginRequest) {
		User user = userRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");
		}

		JwtToken jwtToken = jwtTokenProvider.generateToken(user.getUserId());

		return jwtToken;
	}

	//이메일 인증
	@Transactional
	public String sendVerificationEmail(String email){

		Optional<User> user = userRepository.findByEmail(email);

		//존재하지 않는 이메일이면
		if(!user.isPresent()) {
			throw new IllegalArgumentException("이메일이 존재하지 않습니다.");
		}

		//이메일에 인증번호보내기

		//1. 인증번호 생성(6자리)
		StringBuilder verificationCode = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int tmp = random.nextInt(10);
			verificationCode.append(tmp);
		}

		System.out.println(email);
		//2. 인증번호 보내기(messageAPI 사용)
		javaMailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
				messageHelper.setFrom("dlawhdfbf12@gmail.com");
				messageHelper.setTo(email);
				messageHelper.setSubject("이메일 인증번호");//일단 새 비밀번호로
				messageHelper.setText(verificationCode.toString());
			}
		});

		//3. 인증번호와 이메일을 redis에 저장




		return "이메일로 인증번호를 확인하세요";
	}

	//어처피 e
	public void resetPassword(UserPasswordResetRequest userPasswordResetRequest) {
		String email = userPasswordResetRequest.getEmail();
		String password = userPasswordResetRequest.getPassword();

		User user = userRepository.findByEmail(email).get();
		//이전 비밀번호랑 같으면 밴해주기
		if (user.getPassword().equals(password))
			throw new IllegalArgumentException("이전 비밀번호와 다르게 설정해야합니다.");

		user.updatePassword(password);

	}

	//user 개인정보 조회
	@Transactional
	public UserInfoResponsse getInfo(String token) {
		Long userId = jwtTokenProvider.getUserId(token);

		User user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("정보를 찾을 수 없습니다."));

		return new UserInfoResponsse(user);
	}

	//user추가정보 입력
	public void updateAdditionalInfo(String token, AdditionalUserInfoRequest additionalUserInfoRequest) {
		Long userId = jwtTokenProvider.getUserId(token);

		User user = userRepository.findByUserId(userId).get();

		if(!validAdditionalInfo(additionalUserInfoRequest)) throw new IllegalArgumentException("잘못된 정보입니다.");

		UserImage userImage = additionalUserInfoRequest.getUserImage();
		userImageRepository.save(userImage);

		List<UserSkill> userSkills = additionalUserInfoRequest.getUserSkills();
		List<UserSkill> newSkills = new ArrayList<>();

		for(UserSkill s : userSkills){

			UserSkill newSkill = UserSkill.builder()
				.user(user)
				.skill(skillRepository.findById(s.getSkill().getSkillId()).get())
				.skillLevel(s.getSkillLevel())
				.build();

			newSkills.add(newSkill);

			userSkillRepository.save(newSkill);
		}

		user.addUserInfo(user, userImage, newSkills, additionalUserInfoRequest);

		userRepository.save(user);

	}

	//user 정보 수정
    @Transactional
	public void updateInfo(String token ,UserUpdateInfoRequest userUpdateInfoRequest) {
		Long userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId).get();

		if (!validUpdateInfo(userUpdateInfoRequest))
			throw new IllegalArgumentException("정보를 찾을 수 없습니다.");

		UserImage userImage = userUpdateInfoRequest.getUserImage();
		userImageRepository.save(userImage);

        List<UserSkill> userSkills = userUpdateInfoRequest.getUserSkills();
        List<UserSkill> newSkills = new ArrayList<>();

        userSkillRepository.deleteByUser(user);

        for(UserSkill s : userSkills){

            UserSkill newSkill = UserSkill.builder()
                .user(user)
                .skill(skillRepository.findById(s.getSkill().getSkillId()).get())
                .skillLevel(s.getSkillLevel())
                .build();

            newSkills.add(newSkill);

            userSkillRepository.save(newSkill);
        }

		user.updateUser(user, userImage, newSkills, userUpdateInfoRequest);

        userRepository.save(user);

	}
	//로그아웃
	@Transactional
	public void logout(String refreshToken){

		refreshTokenRepository.deleteById(refreshToken);

	}


	//회원탈퇴
	//토큰 기반으로 하는것이기 때문에 굳이 확인절차 필요?
	@Transactional
	public void deleteUser(String token) {

		long userId = jwtTokenProvider.getUserId(token);

		userRepository.deleteById(userId);

	}

	//User 정보 수정할 때 검증할 메소드
	public boolean validUpdateInfo(UserUpdateInfoRequest userUpdateInfoRequest) {
        if (userUpdateInfoRequest.getPassword() == null)
			return false;
        if (userUpdateInfoRequest.getName() == null)
			return false;
        if (userUpdateInfoRequest.getBirth() == null)
			return false;
        if (userUpdateInfoRequest.getUserSkills() == null)
			return false;
        if (userUpdateInfoRequest.getReferenceUrl() == null)
			return false;
        if (userUpdateInfoRequest.getField() == null)
			return false;
        if (userUpdateInfoRequest.getUserImage() == null)
			return false;
		return true;
	}

	//User 정보 수정할 때 검증할 메소드
	public boolean validAdditionalInfo(AdditionalUserInfoRequest additionalUserInfoRequest) {
		if (additionalUserInfoRequest.getUserSkills() == null)
			return false;
		if (additionalUserInfoRequest.getReferenceUrl() == null)
			return false;
		if (additionalUserInfoRequest.getField() == null)
			return false;
		if (additionalUserInfoRequest.getUserImage() == null)
			return false;
		return true;
	}



}
