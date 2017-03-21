package bg.pu.fmi

import grails.validation.Validateable
import org.springframework.validation.Errors

/**
 * Created by 1 on 25.2.2017 г..
 */
class Item implements Validateable{
    String id
    String name
    String author
    String theme
    List<Metadata> metadatas
    List<Bitstream> bitstreams

    public Item(){

    }

    public Item(String id, String name){
        this.id = id
        this.name = name
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
