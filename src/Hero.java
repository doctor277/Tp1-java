// Classe de base abstraite pour les personnages de type Héros dans le système RPG
public abstract class Hero {

    // Attributs protégés, permettant un accès dans les classes dérivées
    protected int health;               // Santé actuelle du héros
    protected int maxHealth;            // Santé maximale du héros
    protected int attackPoints;         // Puissance d'attaque du héros
    protected int experience = 0;       // Points d'expérience actuels
    protected int level = 1;            // Niveau actuel du héros
    protected Enemy enemy;              // Ennemi que le héros combat
    protected int nbrOfEnemyDefeated = 0; // Nombre d'ennemis vaincus

    // Constructeur de la classe Hero
    public Hero(int health, int attackPoints) {
        this.health = health;
        this.maxHealth = health;
        this.attackPoints = attackPoints;
        this.enemy = new Enemy(100, 25, 35); // Création d'un ennemi initial
    }

    // Méthode pour combattre un nombre défini d'ennemis
    public boolean fight(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            engageEnemy(); // Engager le combat avec l'ennemi
            if (this.health <= 0)
                return false; // Le héros meurt si sa santé tombe à 0 ou moins
            this.nbrOfEnemyDefeated += 1; // Incrémenter le nombre d'ennemis vaincus
            this.experience += this.enemy.getExperienceGiven(); // Ajouter l'expérience gagnée
            if (this.level <= 99)
                this.checkLevelUp(); // Vérifier si le héros peut monter de niveau
            this.enemy.gainPower(); // Augmenter la puissance de l'ennemi pour le prochain combat
        }
        return true; // Retourne vrai si le héros survit à tous les combats
    }

    // Méthode pour vérifier et gérer la montée de niveau du héros
    protected void checkLevelUp() {
        int nextLevel = this.level + 1;
        // Calculer l'expérience requise pour le prochain niveau
        int experienceNeeded = (int) Math.ceil(50 + nextLevel * 20 * (Math.pow(1.1, nextLevel)));
        if (this.experience >= experienceNeeded) {
            this.level += 1; // Monter de niveau
            this.maxHealth += 12; // Augmenter la santé maximale
            this.health = this.maxHealth; // Restaurer la santé complète
            this.attackPoints += 6; // Augmenter les points d'attaque
            this.experience = 0; // Réinitialiser les points d'expérience
        }
    }

    // Méthode abstraite pour engager le combat avec l'ennemi
    public abstract void engageEnemy();

    // Méthode pour permettre au héros de se reposer et restaurer sa santé
    public void rest() {
        this.health = maxHealth; // Restaurer la santé à son maximum
    }

    // Méthode pour soigner le héros
    public void heal(int n) {
        if (n > this.maxHealth - this.health)
            this.health = this.maxHealth; // Ne pas dépasser la santé maximale
        else
            this.health += n; // Ajouter les points de soin à la santé actuelle
    }

    // Méthode pour entraîner le héros et augmenter ses points d'attaque
    public void train(int n) {
        this.attackPoints += n; // Augmenter les points d'attaque du héros
    }
}

// Classe dérivée AttackHero, spécialisée dans les attaques puissantes
class AttackHero extends Hero {

    public AttackHero(int health, int attackPoints) {
        super(health, attackPoints);
    }

    @Override
    public void engageEnemy() {
        // Implémentation du combat pour un héros de type attaque
        while (true) {
            int enemyHealthLeft = this.enemy.getHealth() - 2 * this.attackPoints;
            this.enemy.setHealth(enemyHealthLeft); // Infliger des dégâts doublés à l'ennemi
            if (this.enemy.getHealth() <= 0)
                return; // Terminer le combat si l'ennemi est vaincu
            this.health -= 2 * this.enemy.getAttackPoints(); // Le héros reçoit des dégâts doublés
            if (this.health <= 0)
                return; // Terminer le combat si le héros meurt
        }
    }
}

// Classe dérivée DefenseHero, spécialisée dans la défense
class DefenseHero extends Hero {

    public DefenseHero(int health, int attackPoints) {
        super(health, attackPoints);
    }

    @Override
    public void engageEnemy() {
        // Implémentation du combat pour un héros de type défense
        while (this.health > 0 && this.enemy.getHealth() > 0) {
            int enemyHealthLeft = this.enemy.getHealth() - this.attackPoints / 2;
            this.enemy.setHealth(enemyHealthLeft); // Infliger la moitié des dégâts normaux à l'ennemi
            if (this.enemy.getHealth() <= 0)
                return; // Terminer le combat si l'ennemi est vaincu
            this.health -= this.enemy.getAttackPoints() / 2; // Le héros reçoit la moitié des dégâts normaux
            if (this.health <= 0)
                return; // Terminer le combat si le héros meurt
        }
    }
}

// Classe dérivée BalancedHero, un héros équilibré en attaque et défense
class BalancedHero extends Hero {

    public BalancedHero(int health, int attackPoints) {
        super(health, attackPoints);
    }

    @Override
    public void engageEnemy() {
        // Implémentation du combat pour un héros de type équilibré
        while (this.health > 0 && this.enemy.getHealth() > 0) {
            int enemyHealthLeft = this.enemy.getHealth() - this.attackPoints;
            this.enemy.setHealth(enemyHealthLeft); // Infliger des dégâts normaux à l'ennemi
            if (this.enemy.getHealth() <= 0)
                return; // Terminer le combat si l'ennemi est vaincu
            this.health -= this.enemy.getAttackPoints(); // Le héros reçoit des dégâts normaux
            if (this.health <= 0)
                return; // Terminer le combat si le héros meurt
        }
    }
}
