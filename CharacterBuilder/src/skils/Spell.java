package skils;

public abstract class Spell implements Skill {
        protected abstract String getSpellName();

        public String Apply() {
            return String.format("Spell %s!", getSpellName());
        }
}
