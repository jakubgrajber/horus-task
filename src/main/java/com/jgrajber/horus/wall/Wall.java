package com.jgrajber.horus.wall;

import com.jgrajber.horus.block.Block;
import com.jgrajber.horus.block.CompositeBlock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = getSeparatedBlocks(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.size();
    }

    private List<Block> getSeparatedBlocks(List<Block> blocks) {
        return blocks.stream().flatMap(block -> {
            if (block instanceof CompositeBlock compositeBlock) {
                return compositeBlock.getBlocks().stream();
            } else {
                return Stream.of(block);
            }
        }).toList();
    }
}
