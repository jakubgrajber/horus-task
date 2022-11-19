package com.jgrajber.horus.wall;

import com.jgrajber.horus.block.Block;
import com.jgrajber.horus.block.BlockImpl;
import com.jgrajber.horus.block.CompositeBlockImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
            new CompositeBlockImpl(COLOR_BLACK, MATERIAL_WOOD),
            new CompositeBlockImpl(COLOR_BLUE, MATERIAL_STONE,
                    new CompositeBlockImpl(COLOR_BLACK, MATERIAL_WOOD,
                            new BlockImpl(COLOR_BLACK, MATERIAL_WOOD),
                            new BlockImpl(COLOR_BLACK, MATERIAL_WOOD)),
                    new CompositeBlockImpl(COLOR_BLUE, MATERIAL_PLASTIC,
                            new CompositeBlockImpl(COLOR_RED, MATERIAL_PLASTIC,
                                    new BlockImpl(COLOR_BLACK, MATERIAL_STONE))),
                    new BlockImpl(COLOR_BLUE, MATERIAL_STONE)));

    static final Wall wall = new Wall(BLOCK_LIST);

    static final int NUMBER_OF_BLOCKS = 9;
    static final int ZERO = 0;
    static final int NUMBER_OF_STONE_BLOCKS = 3;

    @Test
    @DisplayName("Returns any block of the given color")
    void givenWallObjectAndColor_whenFindBlockByColor_thenReturnOptionalBlock() {

        // given wall

        // when
        Optional<Block> blockByColor = wall.findBlockByColor(COLOR_BLACK);

        // then
        assertTrue(blockByColor.isPresent());
        assertEquals(COLOR_BLACK, blockByColor.get().getColor());
    }

    @Test
    @DisplayName("Returns empty Optional when the given color is missing")
    void givenWallObjectAndMissingColor_whenFindBlockByColor_thenReturnEmptyOptionalBlock() {

        // given wall

        // when
        Optional<Block> blockByColor = wall.findBlockByColor(MISSING_COLOR);

        // then
        assertTrue(blockByColor.isEmpty());
        assertEquals(Optional.empty(), blockByColor);
    }

    @Test
    @DisplayName("Returns a list of all Blocks with the given material")
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
    @DisplayName("Returns an empty list when no blocks with the given material are found")
    void givenWallObjectAndMissingMaterial_whenFindBlocksByMaterial_thenReturnEmptyList() {

        // given wall

        // when
        List<Block> stoneBlocks = wall.findBlocksByMaterial(MISSING_MATERIAL);

        // then
        assertEquals(ZERO, stoneBlocks.size());
    }

    @Test
    @DisplayName("Returns zero when the list is empty")
    void givenWallObjectWithEmptyList_whenCount_thenReturnZeroNumberOfBlocks() {

        // given
        Wall wall = new Wall(new ArrayList<>());

        // when
        int count = wall.count();

        // then
        assertEquals(ZERO, count);
    }

    @Test
    @DisplayName("Returns the number of blocks")
    void givenWallObject_whenCount_thenReturnNumberOfBlocks() {

        // given wall

        // when
        int count = wall.count();

        // then
        assertEquals(NUMBER_OF_BLOCKS, count);
    }
}