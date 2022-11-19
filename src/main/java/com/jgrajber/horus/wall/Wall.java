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
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return flattenedBlocks()
                .filter(block -> block.getColor().equalsIgnoreCase(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return flattenedBlocks()
                .filter(block -> block.getMaterial().equalsIgnoreCase(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return flattenedBlocks().toList().size();
    }

    private Stream<Block> flattenedBlocks() {
        return blocks.stream().flatMap(this::streamOfBlock);
    }

    private Stream<Block> streamOfBlock(Block block) {
        if (block instanceof CompositeBlock cb) {
            return unboxCompositeBlock(cb);
        } else {
            return Stream.of(block);
        }
    }

    private Stream<Block> unboxCompositeBlock(CompositeBlock compositeBlock) {
        return Stream.concat(
                Stream.of(compositeBlock),
                compositeBlock.getBlocks().stream()
                        .flatMap(this::streamOfBlock));
    }
}
