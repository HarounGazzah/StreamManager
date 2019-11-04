package cl.streamlink.contact.utils.enums;

public enum DurationType {

    allDay(1),halfDay(0.5f),none(0);
    
    float duration;


    DurationType(float duration) {
        this.duration=duration;
    }

    public float getDuration() {
        return duration;
    }
}
