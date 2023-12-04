package skils;

import enums.DamageType;

public interface Target {

    String getName();

    float damage(DamageType type, float value);

}
