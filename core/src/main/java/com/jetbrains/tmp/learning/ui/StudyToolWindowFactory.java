package com.jetbrains.tmp.learning.ui;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.jetbrains.tmp.learning.StudyTaskManager;
import com.jetbrains.tmp.learning.StudyUtils;
import com.jetbrains.tmp.learning.courseFormat.Course;
import icons.InteractiveLearningIcons;
import org.jetbrains.annotations.NotNull;

public class StudyToolWindowFactory implements ToolWindowFactory, DumbAware {
    public static final String STUDY_TOOL_WINDOW = "Task Description";
    private static final Logger logger = Logger.getInstance(StudyToolWindowFactory.class.getName());


    @Override
    public void createToolWindowContent(@NotNull final Project project, @NotNull final ToolWindow toolWindow) {
        toolWindow.setIcon(InteractiveLearningIcons.TaskDescription);
        StudyTaskManager taskManager = StudyTaskManager.getInstance(project);
        final Course course = taskManager.getCourse();
        if (course != null || !taskManager.getUser().getEmail().isEmpty()) {
            logger.info("Study Tool Window is created");
            final StudyToolWindow studyToolWindow;
            if (StudyUtils.hasJavaFx() && taskManager.shouldUseJavaFx()) {
                studyToolWindow = new StudyJavaFxToolWindow();
            } else {
                studyToolWindow = new StudySwingToolWindow();
            }
            studyToolWindow.init(project, true);
            final ContentManager contentManager = toolWindow.getContentManager();
            final Content content = contentManager.getFactory().createContent(studyToolWindow, null, false);
            contentManager.addContent(content);
            Disposer.register(project, studyToolWindow);
        } else {
            logger.warn("Study Tool Window did not create");
        }
    }
}
