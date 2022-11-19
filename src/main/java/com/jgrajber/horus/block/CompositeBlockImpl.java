package com.jgrajber.horus.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {

    private final List<Block> blocks = new ArrayList<>();
    private final String color;
    private final String material;

    public CompositeBlockImpl(String color, String material, Block... blocks) {
        this.color = color;
        this.material = material;
        this.blocks.addAll(Arrays.asList(blocks));
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
