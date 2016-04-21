package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.util.Numeric;

/**
 *
 */
public class ParameterPolice {
    
    public static String[] process(String[] params) {
        for (int param = 0; param < params.length; param++) {
            String pm = params[param];
            switch (param) {
                case 0:
                    pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<address error>";
                    }
                    break;
                case 1:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<annote error>";
                    }
                    break;
                case 2:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<author error>";
                    }
                    break;
                case 3:
                      pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<booktitle error>";
                    }
                    break;
                case 4:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<chapter error>";
                    }
                    break;
                case 5:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<crossref error>";
                    }
                    break;
                case 6:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<edition error>";
                    }
                    break;
                case 7:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<editor error>";
                    }
                    break;
                case 8:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<how published error>";
                    }
                    break;
                case 9:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<institution error>";
                    }
                    break;
                case 10:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<journal error>";
                    }
                    break;
                case 11:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<key error>";
                    }
                    break;
                case 12:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<month error>";
                    }
                    break;
                case 13:
                       pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<note error>";
                    }
                    break;
                case 14:
                     
                    if (params[param] != null) {
                        pm = StringValidator.Validate(pm);
                         if (DataValidator.Validate(pm, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
                             params[param]=pm;
                         } else {
                             params[param]="<NaN>";
                        
                        }
                    }
                    break;
                case 15:
                          pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<organization error>";
                    }
                    break;
                case 16:
                          pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.RANGE_OF_NUMBERS)==DataValidator.RANGE_OF_NUMBERS) {
                        params[param]=DataValidator.formatRangeOfNumbers(pm);
                    } else if (DataValidator.Validate(pm, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER){
                        params[param]=pm;
                    } else {
                             params[param]="<NaN>";
                    }
                    break;
                case 17:
                              pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<publisher error>";
                    }
                    break;
                case 18:
                              pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<school error>";
                    }
                    break;
                case 19:
                              pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<series error>";
                    }
                    break;
                case 20:
                              pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<title error>";
                    }
                    break;
                case 21:
                              pm = StringValidator.Validate(pm);
                    if (DataValidator.Validate(pm, DataValidator.TEXT)==DataValidator.TEXT) {
                        params[param]=pm;
                    } else {
                        params[param]="<type error>";
                    }
                    break;
                case 22:
                     if (params[param] != null) {
                        pm = StringValidator.Validate(pm);
                         if (DataValidator.Validate(pm, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
                             params[param]=pm;
                         } else {
                             params[param]="<NaN>";
                        
                        }
                    }
                    break;
                case 23:
                  if (params[param] != null) {
                        pm = StringValidator.Validate(pm);
                         if (DataValidator.Validate(pm, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
                             params[param]=pm;
                         } else {
                             params[param]="<NaN>";
                        
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return params;
    }
 
    public static String[] clean(String[] params) {
        for (int i = 0; i<params.length; i++) {
            String pm = params[i];
            if (pm!=null) {
            if (pm.contains("error>") || pm=="<empty>" || pm=="<NaN>" || pm=="<null>") {
                params[i]=null;
            }
            }
        }
        return params;
    }
}
