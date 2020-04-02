package cs3500.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.ReadOnlyModel;

/**
 * The text view class that will generate a text file with a description of the animation. This view
 * class will take care of creating a new output file if it does not exist, writing the animation
 * description generated by the model to the output file, and closing the file when done.
 */
public class TextView implements IView {

  private String outputFileName;
  private final ReadOnlyModel readOnlyModel;

  /**
   * Initializing constructor for the text view class. Takes in the readonly model for shape
   * information retrieval because the text view should not be able to mutate the model.
   *
   * @param m the given readonly model
   */
  public TextView(ReadOnlyModel m) {
    this.readOnlyModel = m;
  }

  @Override
  public void refresh() {
    // no need to refresh
    throw new UnsupportedOperationException("This function is not supported in text view.");
  }

  @Override
  public void render() {
    File file = new File(outputFileName);
    // Create the output file first if it does not exist
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException ioe) {
        throw new IllegalStateException("Output file creation failed.");
      }
    }
    // Write to the text file
    try {
      FileWriter fileWriter = new FileWriter(outputFileName);
      fileWriter.write(String.join(" ", "canvas",
              Integer.toString(readOnlyModel.getCanvasX()),
              Integer.toString(readOnlyModel.getCanvasY()),
              Integer.toString(readOnlyModel.getCanvasWidth()),
              Integer.toString(readOnlyModel.getCanvasHeight())) + "\n");
      fileWriter.write(readOnlyModel.toString());
      fileWriter.close();
    } catch (IOException ioe) {
      throw new IllegalStateException("Failed to write to the output file.");
    }
  }

  @Override
  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  @Override
  public void setDelay(int delay) {
    // do nothing
  }

}
