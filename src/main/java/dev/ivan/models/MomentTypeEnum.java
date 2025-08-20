package dev.ivan.models;

public enum MomentTypeEnum {
BUENO("Bueno"),
MALO("Malo");

private final String displayName;

MomentTypeEnum(String displayName) {
this.displayName = displayName;
}

public String getDisplayName() {
return displayName;
}

}
