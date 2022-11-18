package com.jgrajber.horus.wall;

import com.jgrajber.horus.block.Block;
import com.jgrajber.horus.block.BlockImpl;
import com.jgrajber.horus.block.CompositeBlockImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WallTest {

    static final String COLOR_RED = "red";
    static final String COLOR_BLUE = "blue";
    static final String COLOR_BLACK = "black";
    static final String MATERIAL_STONE = "stone";
    static final String MATERIAL_WOOD = "wood";
    static final String MATERIAL_PLASTIC = "plastic";

    static final String MISSING_COLOR = "missing color";
    static final String MISSING_MATERIAL = "missing material";


    static final List<Block> BLOCK_LIST = List.of(
            new CompositeBlockImpl(List.of(
                    new CompositeBlockImpl(List.of(
                            new BlockImpl(COLOR_BLUE, MATERIAL_STONE),
                            new BlockImpl(COLOR_RED, MATERIAL_WOOD)
                    ), COLOR_BLACK, MATERIAL_PLASTIC),
                    new CompositeBlockImpl(List.of(
                            new BlockImpl(COLOR_BLACK, MATERIAL_WOOD)
                    ), COLOR_BLACK, MATERIAL_WOOD)
            ), COLOR_RED, MATERIAL_STONE));

    static final Wall wall = new Wall(BLOCK_LIST);
    static final int NUMBER_OF_BLOCKS = 6;
    static final int ZERO = 0;
    static final int NUMBER_OF_STONE_BLOCKS = 2;

    @Test
    @DisplayName("Returns any block with given colour")
    void givenWallObjectAndColor_whenFindBlockByColor_thenReturnOptionalBlock() {

        // given wall

        // when
        Optional<Block> blockByColor = wall.findBlockByColor(COLOR_RED);

        // then
        assertTrue(blockByColor.isPresent());
        assertEquals(COLOR_RED, blockByColor.get().getColor());
    }

    @Test
    @DisplayName("Returns empty Optional when given color is missing")
    void givenWallObjectAndMissingColor_whenFindBlockByColor_thenReturnOptionalBlock() {

        // given wall

        // when
        Optional<Block> blockByColor = wall.findBlockByColor(MISSING_COLOR);

        // then
        assertTrue(blockByColor.isEmpty());
        assertEquals(Optional.empty(), blockByColor);
    }

    @Test
    @DisplayName("Returns List of all Blocks with given material")
    void givenWallObjectAndMaterial_whenFindBlocksByMaterial_thenReturnListOfBlocks() {

        // given wall

        // when
        List<Block> stoneBlocks = wall.findBlocksByMaterial(MATERIAL_STONE);

        // then
        assertEquals(NUMBER_OF_STONE_BLOCKS, stoneBlocks.size());
        assertTrue(stoneBlocks.stream()
                .allMatch(block -> block.getMaterial().equalsIgnoreCase(MATERIAL_STONE)));
    }

    @Test
    void givenWallObjectAndMissingMaterial_whenFindBlocksByMaterial_thenReturnEmptyList() {

        // given wall

        // when
        List<Block> stoneBlocks = wall.findBlocksByMaterial(MISSING_MATERIAL);

        // then
        assertEquals(ZERO, stoneBlocks.size());
    }

    @Test
    @DisplayName("Returns number of blocks")
    void givenWallObject_whenCount_thenReturnNumberOfBlocks() {

        // given wall

        // when
        int count = wall.count();

        // then
        assertEquals(NUMBER_OF_BLOCKS, count);
    }
}