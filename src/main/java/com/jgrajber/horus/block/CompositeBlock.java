package com.jgrajber.horus.block;

import java.util.List;

public interface CompositeBlock extends Block {

    List<Block> getBlocks();
}
