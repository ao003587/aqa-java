package skils;

import enums.ImpactType;

public interface Target {

    String getName();

    float damage(ImpactType type, float value);

    float heal(ImpactType type, float value);
}
