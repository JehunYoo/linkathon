package com.link.back.entity;

public enum ProjectStatus {

	REGISTERED() {
		@Override
		public ProjectStatus nextStatus() {
			return OPENED;
		}
	}, OPENED() {
		@Override
		public ProjectStatus nextStatus() {
			return CLOSED;
		}
	}, CLOSED() {
		@Override
		public ProjectStatus nextStatus() {
			return this;
		}
	};

	abstract public ProjectStatus nextStatus();
}
