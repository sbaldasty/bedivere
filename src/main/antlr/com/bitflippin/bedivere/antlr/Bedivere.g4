grammar Bedivere;

argmap : EOF ;

idlist : (ID DOTSEP)* ;

ideology : ;

source :
    IDHDR id
    TITLEHDR title
    URLHDR url
    DESCRHDR descr ;

support :
    DESCRHDR descr
    STRENGTHHDR strength NEWLINE
    EVIDENCEHDR idlist NEWLINE ;

idline : IDHDR ID NEWLINE ;
titleline : TITLEHDR STRING ;
descrline : DESCRHDR STRING ;
urlline : URLHDR STRING ;
enthememeline : ENTHEMEMEHDR yesno ;
confidenceline : CONFIDENCEHDR confidence NEWLINE ;
strengthline : STRENGTHHDR strength NEWLINE ;
evidenceline : EVIDENCEHDR idlist NEWLINE ;

id : NUMBER ;
title : STRING ;
descr : STRING ;
url : STRING ;
enthememe : yesno ;
confidence : TRUE | LIKELY | NEUTRAL | UNLIKELY | FALSE ;
strength : SOUND | STRONG | MODERATE | WEAK | NONE ;
yesno : YES | NO ;

IDHDR : WHITESPACE* 'id: ' ;
TITLEHDR : WHITESPACE* 'title: ' ;
DESCRHDR : WHITESPACE* 'descr: ' ;
URLHDR : WHITESPACE* 'url: ' ;
ENTHEMEMEHDR : WHITESPACE* 'enthememe: ' ;
CONFIDENCEHDR : WHITESPACE* 'confidence: ' ;
STRENGTHHDR : WHITESPACE* 'strength: ' ;
EVIDENCEHDR : WHITESPACE* 'evidence: ' ;

SOURCES : '[Sources]' ;
CLAIMS : '[Claims]' ;
IDEOLOGIES : '[Ideologies]' ;

TRUE : 'TRUE' ;
LIKELY : 'LIKELY' ;
NEUTRAL : 'NEUTRAL' ;
UNLIKELY : 'UNLIKELY' ;
FALSE : 'FALSE' ;

SOUND : 'SOUND' ;
STRONG : 'STRONG' ;
MODERATE : 'MODERATE' ;
WEAK : 'WEAK' ;
NONE : 'NONE' ;

YES : 'YES' ;
NO : 'NO' ;

NUMBER : ('0'..'9')+ ;
STRING : ~[\r\n]* NEWLINE ;
NEWLINE : ('\r'? '\n')+ ;
DOTSEP : '.' WHITESPACE* ;
LBRACE : '{' ;
RBRACE : '}' ;
WHITESPACE : ' ' ;