
package co.unal.examsUnal.BusinessLogic.ServiceClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.unal.examsUnal.BusinessLogic.ServiceClient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetExamsRequired_QNAME = new QName("http://Service.BusinessLogic.humanResources.unal.co/", "getExamsRequired");
    private final static QName _GetExamsRequiredResponse_QNAME = new QName("http://Service.BusinessLogic.humanResources.unal.co/", "getExamsRequiredResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.unal.examsUnal.BusinessLogic.ServiceClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetExamsRequired }
     * 
     */
    public GetExamsRequired createGetExamsRequired() {
        return new GetExamsRequired();
    }

    /**
     * Create an instance of {@link GetExamsRequiredResponse }
     * 
     */
    public GetExamsRequiredResponse createGetExamsRequiredResponse() {
        return new GetExamsRequiredResponse();
    }

    /**
     * Create an instance of {@link UserExams }
     * 
     */
    public UserExams createUserExams() {
        return new UserExams();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExamsRequired }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service.BusinessLogic.humanResources.unal.co/", name = "getExamsRequired")
    public JAXBElement<GetExamsRequired> createGetExamsRequired(GetExamsRequired value) {
        return new JAXBElement<GetExamsRequired>(_GetExamsRequired_QNAME, GetExamsRequired.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExamsRequiredResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service.BusinessLogic.humanResources.unal.co/", name = "getExamsRequiredResponse")
    public JAXBElement<GetExamsRequiredResponse> createGetExamsRequiredResponse(GetExamsRequiredResponse value) {
        return new JAXBElement<GetExamsRequiredResponse>(_GetExamsRequiredResponse_QNAME, GetExamsRequiredResponse.class, null, value);
    }

}
