package dev.ivan.models;

public enum MomentTypeEnum {
Bueno("Bueno"),
Malo("Malo");

private final String displayName;

MomentTypeEnum(String displayName) {
this.displayName = displayName;
}

public String getDisplayName() {
return displayName;
}

}
