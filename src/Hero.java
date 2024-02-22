public class Hero {
    private String nom;
    private int health;
    private int maxHealth;
    private int level;
    private int experience;
    private int attackPoints;

    public Hero(String nom, int health, int maxHealth, int level, int experience, int attackPoints) {
        this.nom = nom;
        this.health = health;
        this.maxHealth = maxHealth;
        this.level = level;
        this.experience = experience;
        this.attackPoints = attackPoints;
    }

    public String getHero() {
        char premiereLettre = this.nom.charAt(0);
        if (premiereLettre == 'A') {
            return "Attaque";
        } else if (premiereLettre == 'D') {
            return "Défense";
        } else {
            return "Équilibre";
        }
    }

    public int getLevel() {
        return this.level;
    }

    public String getNom() {
        return this.nom;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }

    public void setAttackPoints(int newAttackPoints) {
        this.attackPoints = newAttackPoints;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean fight() {
        String type = getHero();

        // Statistiques initiales de l'ennemi
        int initialEnemyHealth = 100;
        int initialEnemyAttack = 25;
        int initialEnemyExperience = 35;

        // Combat entre le héros et l'ennemi
        while (this.isAlive() && initialEnemyHealth > 0) {
            // Calcul des dégâts infligés par le héros à l'ennemi
            if (type.equals("Attaque")) {
                initialEnemyHealth -= this.attackPoints * 2;
            } else if (type.equals("Défense")) {
                initialEnemyHealth -= this.attackPoints / 2;
            } else if (type.equals("Équilibre")) {
                initialEnemyHealth -= this.attackPoints;
            }

            // Vérifier si l'ennemi est mort après l'attaque du héros
            if (initialEnemyHealth <= 0) {
                // Augmenter les statistiques de l'ennemi pour le prochain combat
                initialEnemyHealth += 10;
                initialEnemyAttack += 5;
                initialEnemyExperience += 8;
                gainExp(initialEnemyExperience);
                break; // Sortir de la boucle si l'ennemi est mort
            }

            // Calcul des dégâts infligés par l'ennemi au héros
            this.takeDamage(initialEnemyAttack);
        }

        return this.isAlive();
    }

    public void levelUp() {
        if (this.level < 99) {
            this.level++;
            this.maxHealth += 12;
            this.health = this.maxHealth;
            this.attackPoints += 6;
        }
    }

    public void gainExp(int experiencePoints) {
        this.experience += experiencePoints;

        while (this.experience >= calculerExperience()) {
            levelUp();
        }
    }

    public int calculerExperience() {
        return 50 + this.level * 20 * (int) Math.pow(1.1, this.level);
    }

    public boolean rest() {
        this.health = this.maxHealth;
        return isAlive();
    }

    public boolean heal(int healthPoints) {
        this.health += healthPoints;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        return isAlive();
    }

    public boolean train(int trainingPoints) {
        this.attackPoints += trainingPoints;
        return isAlive();
    }
}
