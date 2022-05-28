package blockchain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

public class Block implements Serializable {
    int leadingZeroes;
    private long id;
    private long timestamp;
    int magicNumber;
    long timeWorking;
    private String previousHash;
    private String thisHash;

    public Block(long id, long timestamp, int leadingZeroes, String previousHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.leadingZeroes = leadingZeroes;
        this.previousHash = previousHash;
        LocalTime start = LocalTime.now();
        this.thisHash = getProvedHash();
        LocalTime end = LocalTime.now();
        this.timeWorking = Duration.between(start, end).toSeconds();
    }

    private void setMagicNumber() {
        Random random = new Random();
        this.magicNumber = random.nextInt();
    }

    private String getProvedHash() {
        String hash = calculateThisHash();
        while (!hash.startsWith("0".repeat(leadingZeroes))) {
            hash = calculateThisHash();
        }
        return hash;
    }

    private String calculateThisHash() {
        setMagicNumber();
        return StringUtil.applySha256(this.toString());
    }

    @Override
    public String toString() {
        return previousHash + timestamp + id + magicNumber;
    }


    public String getHash() {
        return this.thisHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String formatForPrinting() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block:").append(System.lineSeparator())
                .append("Id: ").append(id).append(System.lineSeparator())
                .append("Timestamp: ").append(timestamp).append(System.lineSeparator())
                .append("Magic number: ").append(magicNumber).append(System.lineSeparator())
                .append("Hash of the previous block: ").append(System.lineSeparator())
                .append(previousHash).append(System.lineSeparator())
                .append("Hash of the block:").append(System.lineSeparator())
                .append(thisHash).append(System.lineSeparator())
                .append("Block was generating for ")
                .append(this.timeWorking).append(" seconds");

        return sb.toString();
    }
}
