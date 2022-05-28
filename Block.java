package blockchain;

public class Block {
    private long id;
    private long timestamp;
    private String previousHash;
    private String thisHash;

    public Block(long id, long timestamp, String previousHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.thisHash = calculateThisHash();
    }

    private String calculateThisHash() {
        return StringUtil.applySha256(this.toString());
    }

    @Override
    public String toString() {
        return previousHash + timestamp + id;
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
                .append("Hash of the previous block: ").append(System.lineSeparator())
                .append(previousHash).append(System.lineSeparator())
                .append("Hash of the block:").append(System.lineSeparator())
                .append(thisHash);

        return sb.toString();
    }
}
