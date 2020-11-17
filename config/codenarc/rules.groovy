// RULE DEFINITIONS:
// https://guides.grails.org/grails-codenarc/guide/index.html


ruleset {
    description 'Grails-CodeNarc Project RuleSet'

    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/convention.xml'){
        'CompileStatic' {
            enabled = false
        }
        'NoTabCharacter' {
            enabled = false
        }
    }
    ruleset('rulesets/design.xml')
    ruleset('rulesets/dry.xml')
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/formatting.xml')
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/imports.xml')
    ruleset('rulesets/naming.xml')
    ruleset('rulesets/unnecessary.xml')
    ruleset('rulesets/unused.xml')
}