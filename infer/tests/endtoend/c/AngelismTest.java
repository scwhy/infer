/*
 * Copyright (c) 2013- Facebook.
 * All rights reserved.
 */

package endtoend.c;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.matchers.ResultContainsLineNumbers.containsLines;
import static utils.matchers.ResultContainsExactly.containsExactly;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import utils.InferException;
import utils.InferResults;

public class AngelismTest {

  public static final String SOURCE_FILE =
      "null_dereference/angelism.c";

  public static final String NULL_DEREFERENCE = "NULL_DEREFERENCE";

  private static InferResults inferResults;

  @BeforeClass
  public static void runInfer() throws InterruptedException, IOException {
    inferResults = InferResults.loadCInferResults(
        AngelismTest.class,
        SOURCE_FILE);
  }

  @Test
  public void angelismTest() throws InterruptedException, IOException, InferException {
    String[] procedures = {
        "bake",
    };
    assertThat(
        "Results should contain null pointer dereference error",
        inferResults,
        containsExactly(
            NULL_DEREFERENCE,
            SOURCE_FILE,
            procedures
        )
    );
  }

}
