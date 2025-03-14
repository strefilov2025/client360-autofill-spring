package ru.mtsbank.autocomplete.customer.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.mtsbank.lib.web.annotations.MtsBankResponse;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Данные клиента
 */
@ApiModel(description = "Данные клиента")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-28T14:03:31.041683100+03:00[Europe/Moscow]")
@JsonInclude(JsonInclude.Include.NON_NULL)
@MtsBankResponse
public class Customer implements Serializable {
    private static final long serialVersionUID = -7902841355176837475L;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("lastName")
    private String lastName;

    /**
     * Пол
     */
    public enum GenderEnum {
        MALE("MALE"),

        FEMALE("FEMALE"),

        UNKNOWN("UNKNOWN");

        private String value;

        GenderEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static GenderEnum fromValue(String text) {
            for (GenderEnum b : GenderEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + text + "'");
        }
    }

    @JsonProperty("gender")
    private GenderEnum gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthDate")
    private Date birthDate;

    @JsonProperty("birthPlace")
    private String birthPlace;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("deathDate")
    private Date deathDate;

    @JsonProperty("maritalStatus")
    private String maritalStatus;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("snils")
    private String snils;

    @JsonProperty("vip")
    private Boolean vip;

    @JsonProperty("citizenship")
    private String citizenship;

    @JsonProperty("dependantsCount")
    private String dependantsCount;

    @JsonProperty("sourceChannel")
    private String sourceChannel;

    @JsonProperty("organization")
    private String organization;

    @JsonProperty("job")
    private String job;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("taxResident")
    private Boolean taxResident;

    @JsonProperty("monetaryResident")
    private Boolean monetaryResident;

    @JsonProperty("isFullyIdent")
    private Boolean isFullyIdent;

    @JsonProperty("category")
    private String category;

    @JsonProperty("identificationLevel")
    private String identificationLevel;

    @JsonProperty("emails")
    @Valid
    private List<Email> emails = null;

    @JsonProperty("phones")
    @Valid
    private List<Phone> phones = null;

    @JsonProperty("documents")
    @Valid
    private List<Document> documents = null;

    @JsonProperty("addresses")
    @Valid
    private List<Address> addresses = null;

    @JsonProperty("extIds")
    @Valid
    private List<ExtId> extIds = null;

    @JsonProperty("isDeleted")
    private Boolean isDeleted;

    @JsonProperty("sourceSystem")
    private String sourceSystem;

    public Customer fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Полное имя
     *
     * @return fullName
     */
    @ApiModelProperty(value = "Полное имя")


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Имя
     *
     * @return firstName
     */
    @ApiModelProperty(value = "Имя")


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Customer middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    /**
     * Отчество
     *
     * @return middleName
     */
    @ApiModelProperty(value = "Отчество")


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Фамилия
     *
     * @return lastName
     */
    @ApiModelProperty(value = "Фамилия")


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer gender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Пол
     *
     * @return gender
     */
    @ApiModelProperty(readOnly = true, value = "Пол")


    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Customer birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Дата рождения
     *
     * @return birthDate
     */
    @ApiModelProperty(value = "Дата рождения")

    @Valid

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Customer birthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    /**
     * Место рождения
     *
     * @return birthPlace
     */
    @ApiModelProperty(readOnly = true, value = "Место рождения")


    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Customer deathDate(Date deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    /**
     * Дата смерти
     *
     * @return deathDate
     */
    @ApiModelProperty(readOnly = true, value = "Дата смерти")

    @Valid

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Customer maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    /**
     * Семейное положение
     *
     * @return maritalStatus
     */
    @ApiModelProperty(readOnly = true, value = "Семейное положение")


    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Customer inn(String inn) {
        this.inn = inn;
        return this;
    }

    /**
     * ИНН
     *
     * @return inn
     */
    @ApiModelProperty(readOnly = true, value = "ИНН")


    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Customer snils(String snils) {
        this.snils = snils;
        return this;
    }

    /**
     * СНИЛС
     *
     * @return snils
     */
    @ApiModelProperty(readOnly = true, value = "СНИЛС")


    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public Customer vip(Boolean vip) {
        this.vip = vip;
        return this;
    }

    /**
     * Признак VIP
     *
     * @return vip
     */
    @ApiModelProperty(readOnly = true, value = "Признак VIP")


    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Customer citizenship(String citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    /**
     * Гражданство
     *
     * @return citizenship
     */
    @ApiModelProperty(readOnly = true, value = "Гражданство")


    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Customer dependantsCount(String dependantsCount) {
        this.dependantsCount = dependantsCount;
        return this;
    }

    /**
     * Количество иждивенцев
     *
     * @return dependantsCount
     */
    @ApiModelProperty(readOnly = true, value = "Количество иждивенцев")


    public String getDependantsCount() {
        return dependantsCount;
    }

    public void setDependantsCount(String dependantsCount) {
        this.dependantsCount = dependantsCount;
    }

    public Customer sourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
        return this;
    }

    /**
     * Канал поступления
     *
     * @return sourceChannel
     */
    @ApiModelProperty(readOnly = true, value = "Канал поступления")


    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public Customer organization(String organization) {
        this.organization = organization;
        return this;
    }

    /**
     * Место работы
     *
     * @return organization
     */
    @ApiModelProperty(readOnly = true, value = "Место работы")


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Customer job(String job) {
        this.job = job;
        return this;
    }

    /**
     * Полное наименование должности
     *
     * @return job
     */
    @ApiModelProperty(readOnly = true, value = "Полное наименование должности")


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Customer branch(String branch) {
        this.branch = branch;
        return this;
    }

    /**
     * Отделение обслуживания
     *
     * @return branch
     */
    @ApiModelProperty(readOnly = true, value = "Отделение обслуживания")


    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Customer taxResident(Boolean taxResident) {
        this.taxResident = taxResident;
        return this;
    }

    /**
     * Налоговый резидент
     *
     * @return taxResident
     */
    @ApiModelProperty(readOnly = true, value = "Налоговый резидент")


    public Boolean getTaxResident() {
        return taxResident;
    }

    public void setTaxResident(Boolean taxResident) {
        this.taxResident = taxResident;
    }

    public Customer monetaryResident(Boolean monetaryResident) {
        this.monetaryResident = monetaryResident;
        return this;
    }

    /**
     * Валютный резидент
     *
     * @return monetaryResident
     */
    @ApiModelProperty(readOnly = true, value = "Валютный резидент")


    public Boolean getMonetaryResident() {
        return monetaryResident;
    }

    public void setMonetaryResident(Boolean monetaryResident) {
        this.monetaryResident = monetaryResident;
    }

    public Customer isFullyIdent(Boolean isFullyIdent) {
        this.isFullyIdent = isFullyIdent;
        return this;
    }

    /**
     * Признак полной идентификации
     *
     * @return isFullyIdent
     */
    @ApiModelProperty(readOnly = true, value = "Признак полной идентификации")


    public Boolean getIsFullyIdent() {
        return isFullyIdent;
    }

    public void setIsFullyIdent(Boolean isFullyIdent) {
        this.isFullyIdent = isFullyIdent;
    }

    public Customer category(String category) {
        this.category = category;
        return this;
    }

    /**
     * Категория
     *
     * @return category
     */
    @ApiModelProperty(readOnly = true, value = "Категория")


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customer identificationLevel(String identificationLevel) {
        this.identificationLevel = identificationLevel;
        return this;
    }

    /**
     * Уровень идентификации
     *
     * @return identificationLevel
     */
    @ApiModelProperty(readOnly = true, value = "Уровень идентификации")


    public String getIdentificationLevel() {
        return identificationLevel;
    }

    public void setIdentificationLevel(String identificationLevel) {
        this.identificationLevel = identificationLevel;
    }

    public Customer emails(List<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Customer addEmailsItem(Email emailsItem) {
        if (this.emails == null) {
            this.emails = new ArrayList<Email>();
        }
        this.emails.add(emailsItem);
        return this;
    }

    /**
     * Get emails
     *
     * @return emails
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Customer phones(List<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public Customer addPhonesItem(Phone phonesItem) {
        if (this.phones == null) {
            this.phones = new ArrayList<Phone>();
        }
        this.phones.add(phonesItem);
        return this;
    }

    /**
     * Get phones
     *
     * @return phones
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Customer documents(List<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Customer addDocumentsItem(Document documentsItem) {
        if (this.documents == null) {
            this.documents = new ArrayList<Document>();
        }
        this.documents.add(documentsItem);
        return this;
    }

    /**
     * Get documents
     *
     * @return documents
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Customer addresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Customer addAddressesItem(Address addressesItem) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<Address>();
        }
        this.addresses.add(addressesItem);
        return this;
    }

    /**
     * Get addresses
     *
     * @return addresses
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Customer extIds(List<ExtId> extIds) {
        this.extIds = extIds;
        return this;
    }

    public Customer addExtIdsItem(ExtId extIdsItem) {
        if (this.extIds == null) {
            this.extIds = new ArrayList<ExtId>();
        }
        this.extIds.add(extIdsItem);
        return this;
    }

    /**
     * Get extIds
     *
     * @return extIds
     */
    @ApiModelProperty(readOnly = true, value = "")

    @Valid

    public List<ExtId> getExtIds() {
        return extIds;
    }

    public void setExtIds(List<ExtId> extIds) {
        this.extIds = extIds;
    }

    public Customer isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    /**
     * Признак удаленной записи
     *
     * @return isDeleted
     */
    @ApiModelProperty(readOnly = true, value = "Признак удаленной записи")


    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Customer sourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }

    /**
     * Система-источник записи
     *
     * @return sourceSystem
     */
    @ApiModelProperty(readOnly = true, value = "Система-источник записи")


    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(this.requestId, customer.requestId) &&
                Objects.equals(this.fullName, customer.fullName) &&
                Objects.equals(this.firstName, customer.firstName) &&
                Objects.equals(this.middleName, customer.middleName) &&
                Objects.equals(this.lastName, customer.lastName) &&
                Objects.equals(this.gender, customer.gender) &&
                Objects.equals(this.birthDate, customer.birthDate) &&
                Objects.equals(this.birthPlace, customer.birthPlace) &&
                Objects.equals(this.deathDate, customer.deathDate) &&
                Objects.equals(this.maritalStatus, customer.maritalStatus) &&
                Objects.equals(this.inn, customer.inn) &&
                Objects.equals(this.snils, customer.snils) &&
                Objects.equals(this.vip, customer.vip) &&
                Objects.equals(this.citizenship, customer.citizenship) &&
                Objects.equals(this.dependantsCount, customer.dependantsCount) &&
                Objects.equals(this.sourceChannel, customer.sourceChannel) &&
                Objects.equals(this.organization, customer.organization) &&
                Objects.equals(this.job, customer.job) &&
                Objects.equals(this.branch, customer.branch) &&
                Objects.equals(this.taxResident, customer.taxResident) &&
                Objects.equals(this.monetaryResident, customer.monetaryResident) &&
                Objects.equals(this.isFullyIdent, customer.isFullyIdent) &&
                Objects.equals(this.category, customer.category) &&
                Objects.equals(this.identificationLevel, customer.identificationLevel) &&
                Objects.equals(this.emails, customer.emails) &&
                Objects.equals(this.phones, customer.phones) &&
                Objects.equals(this.documents, customer.documents) &&
                Objects.equals(this.addresses, customer.addresses) &&
                Objects.equals(this.extIds, customer.extIds) &&
                Objects.equals(this.isDeleted, customer.isDeleted) &&
                Objects.equals(this.sourceSystem, customer.sourceSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, firstName, middleName, lastName, gender, birthDate, birthPlace, deathDate, maritalStatus, inn, snils, vip, citizenship, dependantsCount, sourceChannel, organization, job, branch, taxResident, monetaryResident, isFullyIdent, category, identificationLevel, emails, phones, documents, addresses, extIds, isDeleted, sourceSystem, requestId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Customer {\n");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    birthPlace: ").append(toIndentedString(birthPlace)).append("\n");
        sb.append("    deathDate: ").append(toIndentedString(deathDate)).append("\n");
        sb.append("    maritalStatus: ").append(toIndentedString(maritalStatus)).append("\n");
        sb.append("    inn: ").append(toIndentedString(inn)).append("\n");
        sb.append("    snils: ").append(toIndentedString(snils)).append("\n");
        sb.append("    vip: ").append(toIndentedString(vip)).append("\n");
        sb.append("    citizenship: ").append(toIndentedString(citizenship)).append("\n");
        sb.append("    dependantsCount: ").append(toIndentedString(dependantsCount)).append("\n");
        sb.append("    sourceChannel: ").append(toIndentedString(sourceChannel)).append("\n");
        sb.append("    organization: ").append(toIndentedString(organization)).append("\n");
        sb.append("    job: ").append(toIndentedString(job)).append("\n");
        sb.append("    branch: ").append(toIndentedString(branch)).append("\n");
        sb.append("    taxResident: ").append(toIndentedString(taxResident)).append("\n");
        sb.append("    monetaryResident: ").append(toIndentedString(monetaryResident)).append("\n");
        sb.append("    isFullyIdent: ").append(toIndentedString(isFullyIdent)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    identificationLevel: ").append(toIndentedString(identificationLevel)).append("\n");
        sb.append("    emails: ").append(toIndentedString(emails)).append("\n");
        sb.append("    phones: ").append(toIndentedString(phones)).append("\n");
        sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
        sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
        sb.append("    extIds: ").append(toIndentedString(extIds)).append("\n");
        sb.append("    isDeleted: ").append(toIndentedString(isDeleted)).append("\n");
        sb.append("    sourceSystem: ").append(toIndentedString(sourceSystem)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

