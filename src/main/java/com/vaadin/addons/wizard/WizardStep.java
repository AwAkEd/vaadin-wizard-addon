package com.vaadin.addons.wizard;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Class used to represent the wizards steps.
 * 
 * @see com.vaadin.ui.CustomComponent
 * 
 * @author javi.serrano@gmail.com
 */
public class WizardStep extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    /** 
     * The base name to step. 
     * Not yet used, but probably must be interesting in future releases. 
     */
    private static final String BASE_NAME = "STEP_";

    /** 
     * The name of the step. 
     */
    private String stepName;

    /** 
     * The step number. 
     */
    private int stepNumber = 0;

    /** 
     * Flag to check if the this step is last of wizard. 
     * Defaults to false because always should be 2 steps. 
     */
    private boolean isLastStep = Boolean.FALSE;

    /** 
     * The main component of this step.
     * The main component is a generic {@link com.vaadin.ui.Component} in order to allow multiple types.
     * 
     *  @see com.vaadin.ui.Component
     */
    private Component mainComponent_;


    /**
     * Instances a new default Step. Establish the size of itself to full.
     * 
     * @see com.vaadin.ui.VerticalLayout#setSizeFull() 
     */
    public WizardStep()
    {
        setSizeFull();
    }

    /**
     * @param stepNumber
     * @param isLastStep
     */
    public WizardStep( int stepNumber, boolean isLastStep )
    {
        this();
        this.stepNumber = stepNumber;
        this.isLastStep = isLastStep;
        this.setStepName( BASE_NAME + stepNumber );
    }

    /**
     * @param stepNumber
     * @param isLastStep
     * @param mainComponent
     */
    public WizardStep( int stepNumber, boolean isLastStep, Component mainComponent )
    {
        this(stepNumber, isLastStep);
        this.mainComponent_ = mainComponent;
        addComponent( mainComponent );
        setExpandRatio( mainComponent, 1 );
    }

    /**
     * @param stepName the stepName to set
     */
    public void setStepName( String stepName )
    {
        this.stepName = stepName;
    }

    /**
     * @return the stepName
     */
    public String getStepName()
    {
        return stepName;
    }

    /**
     * @param stepNumber
     */
    public void setStepNumber( int stepNumber )
    {
        this.stepNumber = stepNumber;
    }

    /**
     * @return
     */
    public int getStepNumber()
    {
        return stepNumber;
    }

    /**
     * @param isLastStep
     */
    public void setLastStep( boolean isLastStep )
    {
        this.isLastStep = isLastStep;
    }

    /**
     * @return
     */
    public boolean isLastStep()
    {
        return isLastStep;
    }

    /**
     * @param mainComponent
     */
    public void setMainComponent( Component mainComponent )
    {
        addComponent( mainComponent );
        setExpandRatio( mainComponent, 1 );
        this.mainComponent_ = mainComponent;
    }

    /**
     * @return
     */
    public Component getMainComponent()
    {
        return mainComponent_;
    }

}
