package com.vaadin.addons.wizard;

import java.util.ArrayList;
import java.util.Iterator;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * Class used to implements the wizard. This class extends
 * {@link com.vaadin.ui.VerticalLayout} to represent the Wizard.
 *
 * @author javi(dot)serrano
 */
public class Wizard extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    /**
    *
    */
    private TabSheet stepTabs = null;

    /**
    *
    */
    private ArrayList<WizardStep> stepList;

    /**
    *
    */
    private int activeStep;

    /**
    *
    */
    private Button previous;

    /**
    *
    */
    private Button next;

    /**
    *
    */
    private Button finish;

    /**
    *
    */
    public Wizard() {
        setSpacing(true);
        setSizeFull();

        stepTabs = new TabSheet();
        stepTabs.setSizeFull();
        addComponent(stepTabs);

        activeStep = 0;
        stepList = new ArrayList<WizardStep>();
    }

    /**
    * @return
    */
    public ArrayList<WizardStep> getStepList() {
        return stepList;
    }

    /**
    * @param stepList
    */
    public void setStepList(ArrayList<WizardStep> stepList) {
        this.stepList = stepList;
    }

    /**
    * @return
    */
    public int getActiveStep() {
        return activeStep;
    }

    /**
    * @param activeStep
    */
    public void setActiveStep(int activeStep) {
        this.activeStep = activeStep;
    }

    /**
    * @return
    */
    public Button getPrevious() {
        return previous;
    }

    /**
    * @param previous
    */
    public void setPrevious(Button previous) {
        this.previous = previous;
    }

    /**
    * @return
    */
    public Button getNext() {
        return next;
    }

    /**
    * @param next
    */
    public void setNext(Button next) {
        this.next = next;
    }

    /**
    * @return
    */
    public Button getFinish() {
        return finish;
    }

    /**
    * @param finish
    */
    public void setFinish(Button finish) {
        this.finish = finish;
    }

    /**
    * @param steps
    */
    public void addSteps(ArrayList<WizardStep> steps) {
        Iterator<WizardStep> iter = steps.iterator();
        while (iter.hasNext()) {
            WizardStep step = iter.next();
            addStep(step);
        }
    }

    /**
    * @param step
    */
    public void addStep(final WizardStep step) {
        addComponent(step);
        setExpandRatio(step, 1);
        setSizeFull();
        HorizontalLayout navigationPanel = new HorizontalLayout();
        navigationPanel.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        step.addComponent(navigationPanel);
        step.setExpandRatio(navigationPanel, 0);

        if (step.getStepNumber() != 0) {
            previous = new Button(WizardButtons.PREVIOUS.name().toLowerCase());
            previous.setData(WizardButtons.PREVIOUS);
            navigationPanel.addComponent(previous);
            navigationPanel.setComponentAlignment(previous,
                    Alignment.BOTTOM_LEFT);
        }

        if (!step.isLastStep()) {
            next = new Button(WizardButtons.NEXT.name().toLowerCase());
            next.setData(WizardButtons.NEXT);
            navigationPanel.addComponent(next);
            navigationPanel.setComponentAlignment(next, Alignment.BOTTOM_RIGHT);
        } else {
            finish = new Button(WizardButtons.FINISH.name().toLowerCase());
            finish.setData(WizardButtons.FINISH);
            navigationPanel.addComponent(finish);
            navigationPanel.setComponentAlignment(finish,
                    Alignment.BOTTOM_RIGHT);
        }
        stepList.add(step);
        Tab stepTab = stepTabs.addTab(step, "Step " + stepList.size(), null);
        stepTab.setEnabled(false);
        if (stepList.size() == 1) {
            stepTabs.setSelectedTab(step);
            stepTab.setEnabled(true);
        }

    }

    /**
    * @param thisStep
    */
    public void nextStep(int thisStep) {
        if ((activeStep == thisStep) && (thisStep < stepList.size())
                && (activeStep != stepList.size())) {
            activeStep++;
            WizardStep nextStep = stepList.get(activeStep);
            nextStep.setEnabled(true);
            stepTabs.getTab(nextStep).setEnabled(true);
            stepTabs.setSelectedTab(nextStep);
        }

        if (stepList.indexOf(thisStep) < stepList.size()) {
            stepTabs.getTab(stepList.get(activeStep)).setEnabled(true);
            stepTabs.setSelectedTab(stepList.get(activeStep));
        }
    }

    /**
    * @param thisStep
    */
    public void previousStep(int thisStep) {
        if (thisStep > 0) {
            activeStep--;
            WizardStep previousStep = stepList.get(activeStep);
            previousStep.setEnabled(true);

            stepTabs.setSelectedTab(previousStep);
        }
    }

    /**
    * @see com.vaadin.ui.AbstractOrderedLayout#paintContent(com.vaadin.terminal.PaintTarget)
    */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        target.addVariable(this, "height", getHeight() + UNIT_SYMBOLS[getHeightUnits()]);
        target.addVariable(this, "widht", getWidth() + UNIT_SYMBOLS[getWidthUnits()]);
    }



}
