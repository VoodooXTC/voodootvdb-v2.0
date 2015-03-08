
package com.joss.voodootvdb.api.models.People;

import com.google.gson.annotations.Expose;

public class Sound {

    @Expose
    private String job;
    @Expose
    private Person person;

    /**
     * 
     * @return
     *     The job
     */
    public String getJob() {
        return job == null ? "" : job;
    }

    /**
     * 
     * @param job
     *     The job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 
     * @return
     *     The person
     */
    public Person getPerson() {
        return person == null ? new Person() : person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

}
