package com.travel.travel.utility;

public class Constants {
    public enum GuideStatus{
        APPOINTED("Назначен"),
        PENDING("В ожидании"),
        PROCESSING("В процессе"),
        DONE("Исполнено");

        private String status;

        private GuideStatus(String status) {
            this.status = status;
        }
        public String value(){return this.status;}
    }

    public enum GuideTime{
        MORNING("Утро"),
        AFTERNOON("День"),
        EVENING("Вечер"),
        NIGHT("Ночь"),
        DEFAULT("");

        private String time;

        private GuideTime(String time) {
            this.time = time;
        }
        public String value(){return this.time;}
    }

    public enum IsGuideAssigned {
        YES("Да"),
        NO("Нет");

        private String isGuideAssigned;

        private IsGuideAssigned(String isGuideAssigned) {
            this.isGuideAssigned = isGuideAssigned;
        }
        public String value(){return this.isGuideAssigned;}
    }
}
