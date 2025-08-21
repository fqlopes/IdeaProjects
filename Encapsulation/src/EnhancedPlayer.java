public class EnhancedPlayer {

    private String fullName;
    private int healthPercent;
    private String weapon;


    public EnhancedPlayer(String fullName, int healthPercent, String weapon) {

        if (healthPercent >= 100){
            this.healthPercent = 100;
        }
        if (healthPercent <= 0){
            this.healthPercent =1;
        }
        this.fullName = fullName;
        this.weapon = weapon;
    }

    public EnhancedPlayer(String fullName) {
        this(fullName, 100, "Axe");
    }

    public void loseHealth (int damage){

        healthPercent = healthPercent - damage;
        if (healthPercent <= 0){
            System.out.println("Player got knocked out of the game");
        }
    }

    public int healthRemaining(){
        return healthPercent;
    }

    public int restoreHealth (int extraHealth){
        healthPercent = healthPercent + extraHealth;
        if (healthPercent > 100){
            healthPercent = 100;
            System.out.println("Health was fully restored");
        }
        return healthPercent;
    }

    @Override
    public String toString() {
        return "EnhancedPlayer{" +
                "name='" + fullName + '\'' +
                ", health=" + healthPercent +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
