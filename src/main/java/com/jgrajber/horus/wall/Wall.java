package com.jgrajber.horus.wall;

import com.jgrajber.horus.block.Block;
import com.jgrajber.horus.block.CompositeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return streamOfUnboxedBlocks()
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return streamOfUnboxedBlocks()
                .filter(block -> block.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return streamOfUnboxedBlocks().toList().size();
    }

    private Stream<Block> streamOfUnboxedBlocks() {

        return blocks.stream().flatMap(block -> {
            if (block instanceof CompositeBlock compositeBlock) {
                return unboxCompositeBlock(compositeBlock).stream();
            } else {
                return Stream.of(block);
            }
        });
    }

    private List<Block> unboxCompositeBlock(CompositeBlock compositeBlock) {

        List<Block> result = new ArrayList<>();
        result.add(compositeBlock);

        List<Block> innerBlocks = compositeBlock.getBlocks();

        for (var block : innerBlocks) {
            if (block instanceof CompositeBlock cb) {
                result.addAll(unboxCompositeBlock(cb));
            } else {
                result.add(block);
            }
        }

        return result;
    }
}
