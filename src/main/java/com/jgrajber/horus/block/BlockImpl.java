package com.jgrajber.horus.block;

public class BlockImpl implements Block {

    private final String color;
    private final String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    public BlockImpl(Block block) {
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
}
