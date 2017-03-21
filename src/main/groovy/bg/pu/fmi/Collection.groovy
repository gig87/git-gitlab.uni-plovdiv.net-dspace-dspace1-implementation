package bg.pu.fmi

import grails.validation.Validateable
import org.springframework.validation.Errors

/**
 * Created by 1 on 20.2.2017 г..
 */
class Collection implements Validateable{
    String id
    String name
    String shortDescription
    List<Item> items

    public Collection(){

    }

    public Collection(String id, String name, String shortDescription){
        this.id = id
        this.name = name
        this.shortDescription = shortDescription
    }

    @Override
    Errors getErrors() {
        return null
    }

    @Override
    Boolean hasErrors() {
        return null
    }

    @Override
    void clearErrors() {

    }

    @Override
    boolean validate() {
        return false
    }

    @Override
    boolean validate(Closure<?>... adHocConstraintsClosures) {
        return false
    }

    @Override
    boolean validate(Map<String, Object> params) {
        return false
    }

    @Override
    boolean validate(Map<String, Object> params, Closure<?>... adHocConstraintsClosures) {
        return false
    }

    @Override
    boolean validate(List fieldsToValidate) {
        return false
    }

    @Override
    boolean validate(List fieldsToValidate, Closure<?>... adHocConstraintsClosures) {
        return false
    }

    @Override
    boolean validate(List fieldsToValidate, Map<String, Object> params) {
        return false
    }

    @Override
    boolean validate(List fieldsToValidate, Map<String, Object> params, Closure<?>... adHocConstraintsClosures) {
        return false
    }

    @Override
    void setErrors(Errors errors) {

    }
}
