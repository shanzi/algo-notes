public class IDNumberVerification {
    private boolean isRegionCodeValid(String code, String[] validCodes) {
        for (String v : validCodes) {
            if (v.equals(code)) return true;
        }
        return false;
    }

    private boolean isBirthdayValid(String birthday) {
        int year = Integer.parseInt(birthday.substring(0, 4));
        int month = Integer.parseInt(birthday.substring(4, 6));
        int day = Integer.parseInt(birthday.substring(6, 8));

        if (year < 1900 || year > 2011) return false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return day >= 1 && day <= 31;
            default:
                if (month == 2) {
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        return day >= 1 && day <= 29;
                    } else {
                        return day >= 1 && day <= 28;
                    }
                } else if (month >= 1 && month <= 12) {
                    return day >= 1 && day <= 30;
                } else {
                    return false;
                }
        }
    }
    
    
    private String getGender(String seqcode) {
        int seq = Integer.parseInt(seqcode);
        if (seq == 0) return "Invalid";
        if (seq % 2 == 0) return "Female";
        else return "Male";
    }

    private boolean isChechSumValid(String id) {
        char checksum = id.charAt(17);
        int x = 0;
        if (checksum == 'X') x = 10;
        else x = checksum - '0';
        for (int i = 0; i < 17; i++) {
            int v = id.charAt(i) - '0';
            v <<= 17 - i;
            x += v % 11;
        }
        return (x % 11) == 1;
    }

    public String verify(String id, String[] regionCodes) {
        if (!isRegionCodeValid(id.substring(0, 6), regionCodes)) return "Invalid";
        if (!isBirthdayValid(id.substring(6, 14))) return "Invalid";
        if (!isChechSumValid(id)) return "Invalid";
        return getGender(id.substring(14, 17));
    }
}
