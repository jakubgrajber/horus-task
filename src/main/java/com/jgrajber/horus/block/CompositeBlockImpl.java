package com.jgrajber.horus.block;

import java.util.ArrayList;
import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {

    private final List<Block> blocks;
    private final String color;
    private final String material;

    public CompositeBlockImpl(Block block, int amount) {
        blocks = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            blocks.add(new BlockImpl(block));
        }
        this.color = block.getColor();
        this.material = block.getMaterial();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
