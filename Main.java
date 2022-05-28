package blockchain;

import java.io.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        BlockChainFactory factory = new BlockChainFactory();
        Blockchain blockchain = factory.makeBlockChain();


        for (int i = 0; i < 5; i++) {
            System.out.println(blockchain.generateNewBlock().formatForPrinting());
            try {
                saveBlockchain(blockchain);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

//        System.out.println(blockchain.validateBlockChain());

    }

    private static void saveBlockchain(Blockchain blockChain) throws IOException {
        File file = new File("Blockchain.list");
        if (!file.isFile()) {
            file.createNewFile();
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(blockChain);
        out.close();
    }
}
