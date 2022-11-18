package com.jgrajber.horus.block;

import java.util.ArrayList;
import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {

    private final List<Block> blocks;
    private final String color;
    private final String material;

    public CompositeBlockImpl(List<Block> block, String color, String material) {
        blocks = block;
        this.color = color;
        this.material = material;
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
