package org.stepik.plugin.actions;

import com.intellij.openapi.actionSystem.KeyboardShortcut;
import com.intellij.openapi.keymap.KeymapUtil;
import com.jetbrains.tmp.learning.courseFormat.Task;
import com.jetbrains.tmp.learning.navigation.StudyNavigator;
import icons.InteractiveLearningIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StepikPreviousTaskAction extends StepikTaskNavigationAction {
    public static final String ACTION_ID = "STEPIK.PreviousTaskAction";
    public static final String SHORTCUT = "ctrl pressed COMMA";

    public StepikPreviousTaskAction() {
        super("Previous Task (" + KeymapUtil.getShortcutText(new KeyboardShortcut(KeyStroke.getKeyStroke(SHORTCUT),
                null)) + ")", "Navigate to the previous task", InteractiveLearningIcons.Prev);
    }

    @Override
    protected Task getTargetTask(@NotNull final Task sourceTask) {
        return StudyNavigator.previousTask(sourceTask);
    }

    @NotNull
    @Override
    public String getActionId() {
        return ACTION_ID;
    }

    @Nullable
    @Override
    public String[] getShortcuts() {
        return new String[]{SHORTCUT};
    }
}
