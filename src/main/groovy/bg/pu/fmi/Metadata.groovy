package bg.pu.fmi

import grails.validation.Validateable
import org.springframework.validation.Errors

/**
 * Created by 1 on 26.2.2017 г..
 */
class Metadata implements Validateable{
    String key
    String value
    String language

    public Metadata(){

    }

    public Metadata(String key, String value, String language){
        this.key = key
        this.value = value
        this.language = language
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
