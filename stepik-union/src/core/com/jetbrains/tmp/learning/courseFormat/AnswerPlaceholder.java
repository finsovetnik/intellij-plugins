package core.com.jetbrains.tmp.learning.courseFormat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of windows which user should type in
 */

public class AnswerPlaceholder {

  @Expose private List<String> myHints = new ArrayList<String>();

  @SerializedName("possible_answer")
  @Expose private String possibleAnswer = "";

  @SerializedName("offset")
  @Expose
  private int myOffset = -1;

  @Expose private int length = -1;

  private int myIndex = -1;
  private String myTaskText;
  private MyInitialState myInitialState;
  private StudyStatus myStatus = StudyStatus.Unchecked;
  private boolean mySelected = false;
  private boolean myUseLength = true;


  @Transient
  private TaskFile myTaskFile;

  public AnswerPlaceholder() {
  }

  public void initAnswerPlaceholder(final TaskFile file, boolean isRestarted) {
    if (!isRestarted) {
      setInitialState(new MyInitialState(myOffset, length));
      myStatus = file.getTask().getStatus();
    }

    setTaskFile(file);
  }

  public int getIndex() {
    return myIndex;
  }

  public void setIndex(int index) {
    myIndex = index;
  }

  /**
   * in actions {@link AnswerPlaceholder#getRealLength()} should be used
   */
  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @NotNull
  public List<String> getHints() {
    return myHints;
  }

  public void setHints(@Nullable final List<String> hints) {
    myHints = hints;
  }

  public String getPossibleAnswer() {
    return possibleAnswer;
  }

  public void setPossibleAnswer(String possibleAnswer) {
    this.possibleAnswer = possibleAnswer;
  }

  public MyInitialState getInitialState() {
    return myInitialState;
  }

  public void setInitialState(MyInitialState initialState) {
    myInitialState = initialState;
  }

  public String getTaskText() {
    return myTaskText;
  }

  public void setTaskText(String taskText) {
    myTaskText = taskText;
  }

  @Transient
  public TaskFile getTaskFile() {
    return myTaskFile;
  }

  @Transient
  public void setTaskFile(TaskFile taskFile) {
    myTaskFile = taskFile;
  }

  public int getPossibleAnswerLength() {
    return possibleAnswer.length();
  }

  /**
   * Returns window to its initial state
   */
  public void reset() {
    myOffset = myInitialState.getOffset();
    length = myInitialState.getLength();
    if (!myUseLength) {
      possibleAnswer = myTaskText;
    }
  }

  public StudyStatus getStatus() {
    return myStatus;
  }

  public void setStatus(StudyStatus status) {
    myStatus = status;
  }

  public boolean getSelected() {
    return mySelected;
  }

  public void setSelected(boolean selected) {
    mySelected = selected;
  }

  public void init() {
    setInitialState(new MyInitialState(myOffset, myTaskText.length()));
  }

  public boolean getUseLength() {
    return myUseLength;
  }

  /**
   * @return length or possible answer length
   */
  public int getRealLength() {
    return myUseLength ? getLength() : getPossibleAnswerLength();
  }

  public void setUseLength(boolean useLength) {
    myUseLength = useLength;
  }

  public int getOffset() {
    return myOffset;
  }

  public void setOffset(int offset) {
    myOffset = offset;
  }

  public static class MyInitialState {
    private int length = -1;
    private int offset = -1;

    public MyInitialState() {
    }

    public MyInitialState(int initialOffset, int length) {
      this.offset = initialOffset;
      this.length = length;
    }

    public int getLength() {
      return length;
    }

    public void setLength(int length) {
      this.length = length;
    }

    public int getOffset() {
      return offset;
    }

    public void setOffset(int offset) {
      this.offset = offset;
    }
  }
}
