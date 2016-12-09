package com.hashlearning.utils.validators;


import java.util.ArrayList;
import java.util.List;

import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;

public class PasswordChecker {

    private static final String TAG = "Password Checker";

    public static PasswordCheckResult checkPassword(String pass) {

        PasswordCheckResult passwordCheckResult = new PasswordCheckResult();

        LengthRule lengthRule = new LengthRule(8, 20);

        CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();

        // require at least 1 digit in passwords
        charRule.getRules().add(new DigitCharacterRule(1));
        // require at least 1 uppercase character in passwords
        charRule.getRules().add(new UppercaseCharacterRule(1));
        // require at least 1 lowercase character in passwords
        charRule.getRules().add(new LowercaseCharacterRule(1));
        charRule.setNumberOfCharacteristics(3);

        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(lengthRule);
        ruleList.add(charRule);

        PasswordValidator validator = new PasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData(new Password(pass));
        RuleResult result = validator.validate(passwordData);

        if (result.isValid()) {

            System.out.println(TAG + " checkPassword: Valid password");
            passwordCheckResult.setValid(result.isValid());

        } else {

            System.out.println(TAG + " checkPassword: Invalid password");
            passwordCheckResult.setValid(result.isValid());

            for (String msg : validator.getMessages(result)) {
                System.out.println(TAG + " checkPassword: " + msg);
            }
            passwordCheckResult.setMessage(validator.getMessages(result).get(0));
        }

        return passwordCheckResult;

    }
}
