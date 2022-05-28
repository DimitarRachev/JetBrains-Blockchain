package blockchain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    private long lastId;
    private List<Block> blockChainMap;

    public Blockchain() {
        this.lastId = 0;
        this.blockChainMap = new LinkedList<>();
    }

    Block generateNewBlock() {
        long timeStamp = new Date().getTime();
        Long id = ++lastId;
        String lastHash = blockChainMap.isEmpty() ? "0" : blockChainMap.get(blockChainMap.size() - 1).getHash();
        Block block = new Block(id, timeStamp, lastHash);
        blockChainMap.add(block);
        return block;
    }


    boolean validateBlockChain() {
        for (int i = 1; i < blockChainMap.size(); i++) {
            if (!blockChainMap.get(i).getPreviousHash().equals(blockChainMap.get(i - 1).getHash())) {
                return false;
            }
        }
        return true;
    }
}
