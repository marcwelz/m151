public class Application {
    public static void main(String[] args) {

        MorseTree tree = new MorseTree();

        String word = "Haus";

        System.out.println("---- Convert ----");
        System.out.println(tree.convert(word));
        System.out.println("--- Convert done ----");

        String morse = ". . - / . . . / - . - - / . - -";

        System.out.println("---- Deconvert ----");
        System.out.println(tree.deconvert(morse));
        System.out.println("--- Deconvert done ----");
    }
}
