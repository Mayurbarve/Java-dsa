package SystemDesign.Creational.Singleton;

    public class SingoltonMain {
        public static void main(String[] args) {

            CacheManager cm = CacheManager.INSTANCE;
            CacheManager cm2 = CacheManager.INSTANCE;

            System.out.println("Check Point for Same Instance? " + (cm == cm2));

            cm.put("User45", "Hitman", 120);
            cm.put("User7", "Siuuuuuuuuuu");

            System.out.println("For User45: " + "Name: " + cm.get("User45"));
            System.out.println("For User7: " + "Name: " + cm.get("User7"));
            //cm.remove("User45");
            System.out.println("Size: " + cm2.size());
        }
    }
