package resources;

public class ResourceCollection {
    private int _food;
    private int _metal;
    private int _mana;

    private ResourceCollection(int food, int metal, int mana) {
        _food = food;
        _metal = metal;
        _mana = mana;
    }

    public boolean hasEnough(ResourceCollection cost) {
        return _food >= cost._food && _metal >= cost._metal && _mana >= cost._mana;
    }

    public ResourceCollection use(ResourceCollection cost) {
        if (hasEnough(cost)) {
            _food -= cost._food;
            _metal -= cost._metal;
            _mana -= cost._mana;
        }

        return this;
    }

    public ResourceCollection add(ResourceCollection provision) {
        _food += provision._food;
        _metal += provision._metal;
        _mana += provision._mana;

        return this;
    }

    public ResourceCollection move(ResourceCollection other) {
        _food += other._food;
        _metal += other._metal;
        _mana += other._mana;

        other._food = 0;
        other._metal = 0;
        other._mana = 0;

        return this;
    }

    public ResourceCollection takeResources(int amount) {
        ResourceCollection.Builder builder = new Builder();

        if (amount > _mana + _food + _metal) {
            builder.setMana(_mana).setFood(_food).setMetal(_metal);

            _mana = 0;
            _food = 0;
            _metal = 0;

            return builder.build();
        }

        int manaAmount = (int)(amount*Math.random()*0.5);
        int foodAmount = (int)(amount*Math.random()*0.5);
        int metalAmount = amount - manaAmount - foodAmount > 0 ? amount - manaAmount - foodAmount: 0;

        if (manaAmount > _mana) {
            builder.setMana(_mana);
            manaAmount -= _mana;
            _mana = 0;
            foodAmount += manaAmount;
        } else {
            builder.setMana(manaAmount);
            _mana -= manaAmount;
        }

        if (foodAmount > _food) {
            builder.setFood(_food);
            foodAmount -= _food;
            _food = 0;
            metalAmount += foodAmount;
        } else {
            builder.setFood(foodAmount);
            _food -= foodAmount;
        }

        if (metalAmount > _metal) {
            builder.setMetal(_metal);
            _metal = 0;
        } else {
            builder.setMetal(metalAmount);
            _metal -= metalAmount;
        }

        return builder.build();
    }

    @Override
    public String toString() {
        return "Food: " + _food + "\nMetal: " + _metal + "\nMana: " + _mana;
    }

    public static class Builder {
        private int _food = 0;
        private int _metal = 0;
        private int _mana = 0;

        public ResourceCollection build() {
            return new ResourceCollection(_food, _metal, _mana);
        }

        public Builder setFood(int food) {
            if (food >= 0) {
                _food = food;
            }

            return this;
        }

        public Builder setMetal(int metal) {
            if (metal >= 0) {
                _metal = metal;
            }

            return this;
        }

        public Builder setMana(int mana) {
            if (mana >= 0) {
                _mana = mana;
            }

            return this;
        }
    }
}
