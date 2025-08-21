public class Player {

    public String name;
    public int health;
    public String weapon;

    public void loseHealth (int damage){

        health =health - damage;
        if (health <= 0){
            System.out.println("Player got knocked out of the game");
        }
    }

    public int healthRemaining(){
        return health;
    }

    public int restoreHealth (int extraHealth){
        health = health + extraHealth;
        if (health > 100){
            health = 100;
            System.out.println("Health was fully restored");
        }
        return health;
    }
}
