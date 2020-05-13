J$.iids = {"9":[1,10,1,17],"17":[1,18,1,22],"25":[1,10,1,23],"33":[1,10,1,23],"41":[1,10,1,23],"49":[2,13,2,18],"57":[2,13,2,18],"65":[2,13,2,18],"73":[4,1,4,8],"81":[4,13,4,18],"89":[4,1,4,19],"91":[4,1,4,12],"97":[4,1,4,20],"105":[6,1,6,11],"113":[7,11,7,18],"121":[7,11,7,18],"129":[7,3,7,19],"137":[8,3,8,10],"145":[8,15,8,20],"153":[8,3,8,21],"155":[8,3,8,14],"161":[8,3,8,22],"169":[6,12,9,2],"177":[6,12,9,2],"185":[6,12,9,2],"193":[9,3,9,7],"201":[6,1,9,8],"209":[6,1,9,9],"217":[11,1,11,3],"225":[11,18,11,28],"233":[11,29,11,41],"241":[11,1,11,42],"243":[11,1,11,17],"249":[11,1,11,43],"257":[14,9,14,15],"265":[15,8,15,10],"273":[16,12,16,18],"281":[16,19,16,25],"289":[16,26,16,34],"297":[16,11,16,35],"305":[18,7,18,12],"313":[18,13,18,17],"321":[18,13,18,22],"329":[18,7,18,23],"337":[18,7,18,24],"345":[17,8,19,4],"353":[17,8,19,4],"361":[17,8,19,4],"369":[13,13,20,2],"377":[13,13,20,2],"385":[13,13,20,2],"393":[21,1,21,7],"401":[21,15,21,20],"409":[21,1,21,20],"417":[21,1,21,21],"425":[22,1,22,8],"433":[22,13,22,19],"441":[22,13,22,24],"449":[22,1,22,25],"451":[22,1,22,12],"457":[22,1,22,26],"465":[23,1,23,8],"473":[23,13,23,19],"481":[23,13,23,23],"489":[23,1,23,24],"491":[23,1,23,12],"497":[23,1,23,25],"505":[1,1,23,25],"513":[1,1,23,25],"521":[1,1,23,25],"529":[1,1,23,25],"537":[6,12,9,2],"545":[6,12,9,2],"553":[17,8,19,4],"561":[17,8,19,4],"569":[1,1,23,25],"577":[1,1,23,25],"nBranches":0,"originalCodeFileName":"E:\\ChromeDown\\jalangi2-master\\jalangi2-master\\experiments\\hello.js","instrumentedCodeFileName":"E:\\ChromeDown\\jalangi2-master\\jalangi2-master\\experiments\\hello_jalangi_.js","code":"var fs = require(\"fs\");\r\nvar hello = \"var\";\r\n\r\nconsole.log(hello);\r\n\r\nsetTimeout(function() {\r\n  hello = \"hello\";\r\n  console.log(hello);\r\n},1000);\r\n\r\nfs.writeFileSync(\"test.txt\",\"I am test!\");\r\n\r\nvar person ={\r\n  name: \"lisi\",\r\n  age: 21,\r\n  family: [\"lida\",\"lier\",\"wangwu\"],\r\n  say: function(){\r\n      alert(this.name);\r\n  }\r\n};\r\nperson.name = \"RJW\";\r\nconsole.log(person.name);\r\nconsole.log(person.age);"};
jalangiLabel2:
    while (true) {
        try {
            J$.Se(505, 'E:\\ChromeDown\\jalangi2-master\\jalangi2-master\\experiments\\hello_jalangi_.js', 'E:\\ChromeDown\\jalangi2-master\\jalangi2-master\\experiments\\hello.js');
            J$.N(513, 'fs', fs, 0);
            J$.N(521, 'hello', hello, 0);
            J$.N(529, 'person', person, 0);
            var fs = J$.X1(41, J$.W(33, 'fs', J$.F(25, J$.R(9, 'require', require, 2), 0)(J$.T(17, "fs", 21, false)), fs, 3));
            var hello = J$.X1(65, J$.W(57, 'hello', J$.T(49, "var", 21, false), hello, 3));
            J$.X1(97, J$.M(89, J$.R(73, 'console', console, 2), 'log', 0)(J$.R(81, 'hello', hello, 1)));
            J$.X1(209, J$.F(201, J$.R(105, 'setTimeout', setTimeout, 2), 0)(J$.T(185, function () {
                jalangiLabel0:
                    while (true) {
                        try {
                            J$.Fe(169, arguments.callee, this, arguments);
                            arguments = J$.N(177, 'arguments', arguments, 4);
                            J$.X1(129, hello = J$.W(121, 'hello', J$.T(113, "hello", 21, false), hello, 2));
                            J$.X1(161, J$.M(153, J$.R(137, 'console', console, 2), 'log', 0)(J$.R(145, 'hello', hello, 1)));
                        } catch (J$e) {
                            J$.Ex(537, J$e);
                        } finally {
                            if (J$.Fr(545))
                                continue jalangiLabel0;
                            else
                                return J$.Ra();
                        }
                    }
            }, 12, false, 169), J$.T(193, 1000, 22, false)));
            J$.X1(249, J$.M(241, J$.R(217, 'fs', fs, 1), 'writeFileSync', 0)(J$.T(225, "test.txt", 21, false), J$.T(233, "I am test!", 21, false)));
            var person = J$.X1(385, J$.W(377, 'person', J$.T(369, {
                name: J$.T(257, "lisi", 21, false),
                age: J$.T(265, 21, 22, false),
                family: J$.T(297, [
                    J$.T(273, "lida", 21, false),
                    J$.T(281, "lier", 21, false),
                    J$.T(289, "wangwu", 21, false)
                ], 10, false),
                say: J$.T(361, function () {
                    jalangiLabel1:
                        while (true) {
                            try {
                                J$.Fe(345, arguments.callee, this, arguments);
                                arguments = J$.N(353, 'arguments', arguments, 4);
                                J$.X1(337, J$.F(329, J$.R(305, 'alert', alert, 2), 0)(J$.G(321, J$.R(313, 'this', this, 0), 'name', 0)));
                            } catch (J$e) {
                                J$.Ex(553, J$e);
                            } finally {
                                if (J$.Fr(561))
                                    continue jalangiLabel1;
                                else
                                    return J$.Ra();
                            }
                        }
                }, 12, false, 345)
            }, 11, false), person, 3));
            J$.X1(417, J$.P(409, J$.R(393, 'person', person, 1), 'name', J$.T(401, "RJW", 21, false), 0));
            J$.X1(457, J$.M(449, J$.R(425, 'console', console, 2), 'log', 0)(J$.G(441, J$.R(433, 'person', person, 1), 'name', 0)));
            J$.X1(497, J$.M(489, J$.R(465, 'console', console, 2), 'log', 0)(J$.G(481, J$.R(473, 'person', person, 1), 'age', 0)));
        } catch (J$e) {
            J$.Ex(569, J$e);
        } finally {
            if (J$.Sr(577)) {
                J$.L();
                continue jalangiLabel2;
            } else {
                J$.L();
                break jalangiLabel2;
            }
        }
    }
// JALANGI DO NOT INSTRUMENT
