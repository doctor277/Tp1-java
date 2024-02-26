// Classe représentant les ennemis dans le jeu RPG
public class Enemy {

    // Attributs privés pour la santé, les points d'attaque, et l'expérience donnée par l'ennemi
    private int health;              // Santé actuelle de l'ennemi
    private int attackPoints;        // Points d'attaque de l'ennemi
    private int experienceGiven;     // Points d'expérience donnés à la défaite de l'ennemi
    private int initHealth;          // Santé initiale de l'ennemi pour le calcul de gain de puissance
    private int initAttackPoints;    // Points d'attaque initiaux pour le calcul de gain de puissance

    // Constructeur de la classe Enemy
    public Enemy(int health, int attackPoints, int experienceGiven) {
        this.health = health;
        this.attackPoints = attackPoints;
        this.experienceGiven = experienceGiven;
        this.initHealth = health;             // Mémoriser la santé initiale
        this.initAttackPoints = attackPoints; // Mémoriser les points d'attaque initiaux
    }

    // Méthode pour obtenir la santé actuelle de l'ennemi
    public int getHealth() {
        return health;
    }

    // Méthode pour définir la santé de l'ennemi
    public void setHealth(int health) {
        this.health = health;
    }

    // Méthode pour obtenir les points d'attaque de l'ennemi
    public int getAttackPoints() {
        return attackPoints;
    }

    // Méthode pour obtenir les points d'expérience donnés par l'ennemi
    public int getExperienceGiven() {
        return experienceGiven;
    }

    // Méthode pour augmenter la puissance de l'ennemi après chaque combat
    public void gainPower() {
        // Augmenter la santé et les points d'attaque de l'ennemi pour le prochain combat
        this.initHealth = this.health = this.initHealth + 10;
        this.initAttackPoints = this.attackPoints = this.initAttackPoints + 5;
        this.experienceGiven = this.experienceGiven + 8; // Augmenter les points d'expérience donnés
    }
}
