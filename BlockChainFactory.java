package blockchain;

import java.io.*;
import java.util.Scanner;

public class BlockChainFactory {
    Scanner scanner;
    File file;

    public BlockChainFactory() {
        this.scanner = new Scanner(System.in);
        this.file = new File("Blockchain.list");
    }

    Blockchain makeBlockChain() throws IOException, ClassNotFoundException {
        if (file.isFile()) {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Blockchain blockchain = (Blockchain) in.readObject();
            in.close();
            if (blockchain.validateBlockChain()) {
                return blockchain;
            } else {
                return getNewBlockchain();
            }

        } else {
            return getNewBlockchain();
        }
    }

    private Blockchain getNewBlockchain() {
        System.out.print("Enter how many zeros the hash must start with: ");
        int n = scanner.nextInt();
        return new Blockchain(n);
    }
}
