package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TitleFieldKey {
    @SerialName("P0100") RESPONSIBLE_PERSONS_PRIMARY,
    @SerialName("P0101") RESPONSIBLE_PERSONS_SECONDARY,
    @SerialName("C0200") RESPONSIBLE_CORPORATE_PRIMARY,
    @SerialName("C0201") RESPONSIBLE_CORPORATE_SECONDARY,
    @SerialName("PC0001") RESPONSIBILITY_STATEMENT, // unsure
    // all T0* follows MAB2 - check if my guesses for field values are correct
    @SerialName("T0002") CREATION_DATE,
    @SerialName("T0003") INDEX_DATE,
    @SerialName("T0009") PROVENIENCE_CODE,
    @SerialName("T0010") FOREIGN_ID_INTERNAL,
    @SerialName("T0015") LANGUAGE_CODE,
    @SerialName("T0035") COUNTRY_OF_ORIGIN,
    @SerialName("T0060") PUBLICATION_FORM_CODE,
    @SerialName("T0065") PUBLICATION_FORM_NAME,
    @SerialName("T0089") VOLUME_NUMBER,
    @SerialName("T0100") PERSON_ATTRIBUTION,
    @SerialName("T0200") CORPORATE_ATTRIBUTION,
    @SerialName("T0304") TITLE_DESCRIPTION, // unsure
    @SerialName("T0331") MAIN_TITLE,
    @SerialName("T0335") SUBTITLE,
    @SerialName("T0359") RESPONSIBILITY_STATEMENT_RAW,
    @SerialName("T0370") TITLE_SUPPLEMENT, // unsure, RAK-WB?
    @SerialName("T0403") EDITION,
    @SerialName("T0410") PUBLICATION_EVENT,
    @SerialName("T0412") PUBLISHER,
    @SerialName("T0425") RELEASE_YEAR, // unsure
    @SerialName("T0433") PAGES_EXTENT,
    @SerialName("T0434") PAGES_ADDITIONAL_GRAPHICS,
    @SerialName("T0437") PAGES_ADDITIONAL_CONTENT,
    @SerialName("T0435") PAGES_MEASUREMENT,
    @SerialName("T0451") SERIES_TITLE,
    @SerialName("T0453") SERIES_PARENT_FOREIGN_ID,
    @SerialName("T0455") SERIES_COUNTING,
    @SerialName("T0456") SERIES_COUNTING_INDEX,
    @SerialName("T0501") ADDITIONAL_TITLE_DATA, // unsure
    @SerialName("T0505") ADDITIONAL_COVER_DATA, // unsure
    @SerialName("T0519") KIND_OF_WORK, // unsure
    @SerialName("T0540") ISBN_PRETTY,
    @SerialName("T0542") PURCHASE_PRICE,
    @SerialName("T0551") ISBN_LONG_INTL_PRETTY, // unsure
    @SerialName("T0552") ISBN_LONG_INTL_PREFIXED, // unsure
    @SerialName("T0574") CLASSIFICATION_INDEX, // unsure
    @SerialName("T0581") ISBN_LONG_INTL_RAW, // unsure
    @SerialName("T0662") ATTACHMENT_LINK,
    @SerialName("T0663") ATTACHMENT_DESCRIPTION,
    @SerialName("T0673") PLACE_OF_PUBLICATION_RAW, // unsure: RAK-WB?
    @SerialName("T0676") PUBLISHER_RAW, // unsure: RAK-WB?
    @SerialName("T0710") KEYWORDS_RAW,
    @SerialName("T0720") KEYWORDS_STRUCTURED,
    @SerialName("T0750") CONTENT_DESCRIPTION, // unsure
    @SerialName("T0902") KEYWORDS_CHAINED,
    @SerialName("T1024") FOREIGN_ID,
    @SerialName("T1065") EXTERNAL_FOREIGN_ID,
    @SerialName("T1768") RELEASE_REMARK, // unsure
    @SerialName("T1770") TITLE_RAW,
    @SerialName("T1780") ISBN_RAW, // unsure
    @SerialName("T1775") DOI_RAW, // unsure
    @SerialName("T4400") BESTAND_TYPE,
    @SerialName("T5001") ANZAHL_GESAMTTITEL,
    @SerialName("T5100") VOLUME_REMARK,
    @SerialName("T4230") HOLDING_LIBRARY_ISIL,
    @SerialName("T4301") HOLDING_LIBRARY_LANGUAGE, // unsure
    @SerialName("T4662") EBOOK_LINK, // unsure
    @SerialName("T7583") ARCHIVAL_REMARK, // unsure
    @SerialName("S0902") KEYWORDS_902,
    @SerialName("S0907") KEYWORDS_907,
    @SerialName("T0004") UNKNOWN_T0004,
    @SerialName("T0007") UNKNOWN_T0007,
    @SerialName("T0024") UNKNOWN_T0024,
    @SerialName("T0038") UNKNOWN_T0038,
    @SerialName("T0062") UNKNOWN_T0062,
    @SerialName("T0027") UNKNOWN_T0027,
    @SerialName("T0028") UNKNOWN_T0028,
    @SerialName("T0036") UNKNOWN_T0036,
    @SerialName("T0061") UNKNOWN_T0061,
    @SerialName("T0090") UNKNOWN_T0090,
    @SerialName("T0101") UNKNOWN_T0101,
    @SerialName("T0201") UNKNOWN_T0201,
    @SerialName("T0422") UNKNOWN_T0422,
    @SerialName("T0424") UNKNOWN_T0424,
    @SerialName("T0702") UNKNOWN_T0702,
    @SerialName("T0709") UNKNOWN_T0709,
    @SerialName("T0800") UNKNOWN_T0800,
    @SerialName("T0802") UNKNOWN_T0802,
    @SerialName("T0907") UNKNOWN_T0907,
    @SerialName("T1030") UNKNOWN_T1030,
    @SerialName("T1677") UNKNOWN_T1677,
    @SerialName("T1678") UNKNOWN_T1678,
    @SerialName("T1701") UNKNOWN_T1701,
    @SerialName("T1705") UNKNOWN_T1705,
    @SerialName("T1706") UNKNOWN_T1706,
    @SerialName("T1716") UNKNOWN_T1716,
    @SerialName("T1718") UNKNOWN_T1718,
    @SerialName("T1719") UNKNOWN_T1719,
    @SerialName("T1769") UNKNOWN_T1769,
    @SerialName("T1778") UNKNOWN_T1778,
    @SerialName("T1781") UNKNOWN_T1781,
    @SerialName("T2084") UNKNOWN_T2084,
    @SerialName("T4102") UNKNOWN_T4102,
    @SerialName("T4110") UNKNOWN_T4110,
    @SerialName("T4410") UNKNOWN_T4410,
    @SerialName("T4663") UNKNOWN_T4663,
    @SerialName("T5002") UNKNOWN_T5002,
    @SerialName("T5003") UNKNOWN_T5003,
    @SerialName("T5004") UNKNOWN_T5004,
    @SerialName("T5005") UNKNOWN_T5005,
    @SerialName("T5050") UNKNOWN_T5050,
    @SerialName("T5055") UNKNOWN_T5055,
    @SerialName("T5051") UNKNOWN_T5051,
    @SerialName("T7061") UNKNOWN_T7061,
    @SerialName("T7419") UNKNOWN_T7419,
    @SerialName("T7423") UNKNOWN_T7423,
    @SerialName("T7776") UNKNOWN_T7776,
    @SerialName("T7060") UNKNOWN_T7060,
    @SerialName("T7062") UNKNOWN_T7062,
    @SerialName("T7065") UNKNOWN_T7065,
    @SerialName("T7303") UNKNOWN_T7303,
    @SerialName("T7520") UNKNOWN_T7520,
    @SerialName("T7676") UNKNOWN_T7676,
    @SerialName("T7677") UNKNOWN_T7677,
    @SerialName("T7780") UNKNOWN_T7780,
    @SerialName("X0014") UNKNOWN_X0014,
    // TODO hacks to avoid outer-referencing a custom serializer for CatalogueTitle
    @SerialName("popularity") POPULARITY,
}
