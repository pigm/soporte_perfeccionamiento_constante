PGDMP         5             
    x            came    10.5    13.0 4               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    1378230    came    DATABASE     Y   CREATE DATABASE came WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'es_ES.UTF-8';
    DROP DATABASE came;
                adm_came    false            �          0    2392067    came_acciones_mejoras 
   TABLE DATA           �   COPY came.came_acciones_mejoras (id_acciones_mejoras, id_foco, nombre, descripcion, ultima_modificacion, id_usuario_modificacion, fecha_registro) FROM stdin;
    came          adm_came    false    198   �4       �          0    2392076    came_asesoria 
   TABLE DATA           Q   COPY came.came_asesoria (id_asesoria, id_supervisor_establecimiento) FROM stdin;
    came          adm_came    false    199   5                 0    2392389    came_asignacion_maxima 
   TABLE DATA           }   COPY came.came_asignacion_maxima (id_asignacion_maxima, supervisores, foco, id_usuario_registro, fecha_registro) FROM stdin;
    came          adm_came    false    243   25       �          0    2392082    came_asignacion_tipo 
   TABLE DATA           @   COPY came.came_asignacion_tipo (id_asignacion_tipo) FROM stdin;
    came          adm_came    false    200   �5       �          0    2392087 +   came_came_estandares_indicativos_desempenio 
   TABLE DATA           r   COPY came.came_came_estandares_indicativos_desempenio (id_estandares_indicativos_desempenio, id_foco) FROM stdin;
    came          adm_came    false    201   �5       �          0    2392093    came_categorizacion 
   TABLE DATA           >   COPY came.came_categorizacion (id_categorizacion) FROM stdin;
    came          adm_came    false    202   �5       �          0    2392098    came_clasificacion_sep 
   TABLE DATA           D   COPY came.came_clasificacion_sep (id_clasificacion_sep) FROM stdin;
    came          adm_came    false    203   �5       �          0    2392103    came_director 
   TABLE DATA           2   COPY came.came_director (id_director) FROM stdin;
    came          adm_came    false    204   �5       �          0    2392108    came_elemento_lista 
   TABLE DATA           X   COPY came.came_elemento_lista (id_elemento_lista, id_lista, nombre, status) FROM stdin;
    came          adm_came    false    205   6       �          0    2392119    came_establecimiento 
   TABLE DATA           �   COPY came.came_establecimiento (id_establecimiento, id_categorizacion1, id_establecimiento_estado, id_director, id_categorizacion2, id_clasificacion_sep, id_sostenedor, id_deprov, id_comuna) FROM stdin;
    came          adm_came    false    207   W7       �          0    2392130 -   came_establecimiento_categorizacion_historica 
   TABLE DATA           �   COPY came.came_establecimiento_categorizacion_historica (id_establecimiento_categorizacion_historica, id_categorizacion, id_establecimiento) FROM stdin;
    came          adm_came    false    208   t7       �          0    2392138    came_establecimiento_estado 
   TABLE DATA           N   COPY came.came_establecimiento_estado (id_establecimiento_estado) FROM stdin;
    came          adm_came    false    209   �7       �          0    2392143    came_estado 
   TABLE DATA           6   COPY came.came_estado (id_estado, nombre) FROM stdin;
    came          adm_came    false    210   �7       �          0    2392151 	   came_foco 
   TABLE DATA           �   COPY came.came_foco (id_foco, nombre, descripcion, fecha_registro, ultima_modificacion, id_usuario_modificacion, periodo) FROM stdin;
    came          adm_came    false    211   �7       �          0    2392159 
   came_lista 
   TABLE DATA           4   COPY came.came_lista (id_lista, nombre) FROM stdin;
    came          adm_came    false    212   8       �          0    2392167    came_matricula 
   TABLE DATA           f   COPY came.came_matricula (id_matricula, id_matricula_etnia, id_nivel, id_establecimiento) FROM stdin;
    came          adm_came    false    213   �8       �          0    2392175    came_matricula_etnia 
   TABLE DATA           @   COPY came.came_matricula_etnia (id_matricula_etnia) FROM stdin;
    came          adm_came    false    214   �8       �          0    2392180    came_modulo 
   TABLE DATA           I   COPY came.came_modulo (id_modulo, id_estado, nombre, status) FROM stdin;
    came          adm_came    false    215   �8       �          0    2392189    came_movimientos_claves 
   TABLE DATA           �   COPY came.came_movimientos_claves (id_movimientos_claves, id_acciones_mejoras, nombre, descripcion, ultima_modificacion, id_usuario_modificacion, fecha_registro) FROM stdin;
    came          adm_came    false    216   �9       �          0    2392198 
   came_nivel 
   TABLE DATA           ,   COPY came.came_nivel (id_nivel) FROM stdin;
    came          adm_came    false    217   �9       �          0    2392203    came_objetivo_mejora 
   TABLE DATA           Y   COPY came.came_objetivo_mejora (id_objetivo_mejora, id_foco, id_sesion_foco) FROM stdin;
    came          adm_came    false    218   �9       �          0    2392210    came_perfil 
   TABLE DATA           �   COPY came.came_perfil (id_perfil, id_estado, id_perfil_nivel, nombre, descripcion, habilitado, ultima_modificacion, id_usuario_modificacion) FROM stdin;
    came          adm_came    false    219   �9       �          0    2392220    came_perfil_menu 
   TABLE DATA           �   COPY came.came_perfil_menu (id_perfil_menu, id_perfil, id_sub_modulo, id_perfil_menu_acceso, start_date, end_date, status) FROM stdin;
    came          adm_came    false    220   ";       �          0    2392228    came_perfil_menu_acceso 
   TABLE DATA           N   COPY came.came_perfil_menu_acceso (id_perfil_menu_acceso, nombre) FROM stdin;
    came          adm_came    false    221   �=       �          0    2392236    came_perfil_nivel 
   TABLE DATA           B   COPY came.came_perfil_nivel (id_perfil_nivel, nombre) FROM stdin;
    came          adm_came    false    222   �=       �          0    2392244    came_periodo 
   TABLE DATA           [   COPY came.came_periodo (id_periodo, anio, id_usuario_registro, fecha_registro) FROM stdin;
    came          adm_came    false    223   F>                 0    2392383 $   came_periodo_asignacion_supervisores 
   TABLE DATA           �   COPY came.came_periodo_asignacion_supervisores (id_periodo_asignacion_supervisores, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    242   �?                  0    2392267    came_periodo_conformacion_redes 
   TABLE DATA           �   COPY came.came_periodo_conformacion_redes (id_periodo_conformacion_redes, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    226   �A       �          0    2392249 $   came_periodo_documentos_provinciales 
   TABLE DATA           �   COPY came.came_periodo_documentos_provinciales (id_periodo_documentos_provinciales, id_periodo, id_region, id_deprov, visible, es_caso_especial, template_path, fecha_registro, fecha_inicio, fecha_fin, id_usuario_registro, descripcion) FROM stdin;
    came          adm_came    false    224   D       �          0    2392258 "   came_periodo_documentos_regionales 
   TABLE DATA           �   COPY came.came_periodo_documentos_regionales (id_periodo_documentos_regionales, id_periodo, id_region, fecha_inicio, fecha_fin, visible, es_caso_especial, template_path, id_usuario_registro, fecha_registro, descripcion) FROM stdin;
    came          adm_came    false    225   G                 0    2392273 /   came_periodo_planificacion_implementacion_apoyo 
   TABLE DATA           �   COPY came.came_periodo_planificacion_implementacion_apoyo (id_periodo_planificacion_implementacion_apoyo, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    227   �I                 0    2392279    came_red 
   TABLE DATA           X   COPY came.came_red (id_red, id_red_tipo, id_periodo, id_usuario, id_deprov) FROM stdin;
    came          adm_came    false    228   �K                 0    2392287    came_red_establecimiento 
   TABLE DATA           p   COPY came.came_red_establecimiento (id_red_establecimiento, id_red, id_establecimiento, id_periodo) FROM stdin;
    came          adm_came    false    229   L                 0    2392295    came_red_sostenedor 
   TABLE DATA           U   COPY came.came_red_sostenedor (id_red_sostenedor, id_red, id_sostenedor) FROM stdin;
    came          adm_came    false    230   9L                 0    2392302    came_red_tipo 
   TABLE DATA           :   COPY came.came_red_tipo (id_red_tipo, nombre) FROM stdin;
    came          adm_came    false    231   VL                 0    2392310    came_sesion 
   TABLE DATA           N   COPY came.came_sesion (id_sesion, id_asesoria, id_session_estado) FROM stdin;
    came          adm_came    false    232   sL                 0    2392317    came_sesion_estado 
   TABLE DATA           <   COPY came.came_sesion_estado (id_sesion_estado) FROM stdin;
    came          adm_came    false    233   �L                 0    2392322    came_sesion_foco 
   TABLE DATA           L   COPY came.came_sesion_foco (id_sesion_foco, id_sesion, id_foco) FROM stdin;
    came          adm_came    false    234   �L       	          0    2392329    came_sesion_movimientos_claves 
   TABLE DATA           �   COPY came.came_sesion_movimientos_claves (id_sesion_movimientos_claves, id_sesion_foco, id_foco, id_movimientos_claves) FROM stdin;
    came          adm_came    false    235   �L       
          0    2392337    came_sostenedor 
   TABLE DATA           6   COPY came.came_sostenedor (id_sostenedor) FROM stdin;
    came          adm_came    false    236   �L                 0    2392342    came_sub_modulo 
   TABLE DATA           \   COPY came.came_sub_modulo (id_sub_modulo, id_estado, id_modulo, nombre, status) FROM stdin;
    came          adm_came    false    237   M                 0    2392352    came_supervisor 
   TABLE DATA           k   COPY came.came_supervisor (id_supervisor, id_usuario, id_deprov, start_date, end_date, status) FROM stdin;
    came          adm_came    false    238   xN                 0    2392358    came_supervisor_establecimiento 
   TABLE DATA           �   COPY came.came_supervisor_establecimiento (id_supervisor_establecimiento, id_asignacion_tipo, id_supervisor, id_red, id_establecimiento) FROM stdin;
    came          adm_came    false    239   �N                 0    2392394    came_supervisor_suplencia 
   TABLE DATA           �   COPY came.came_supervisor_suplencia (id_supervisor_suplencia, id_supervisor_ausente, id_supervisor_suplente, start_date, end_date, fecha_registro, id_usuario_registro) FROM stdin;
    came          adm_came    false    244   O                 0    2392367    came_usuario 
   TABLE DATA           	  COPY came.came_usuario (id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, nombre, apellido_paterno, apellido_materno, email, habilitado, reintentos, ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro) FROM stdin;
    came          adm_came    false    240   3O                 0    2392376    came_usuario_perfil 
   TABLE DATA           s   COPY came.came_usuario_perfil (id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status) FROM stdin;
    came          adm_came    false    241   �P                  0    0 +   came_establecimiento_id_categorizacion2_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('came.came_establecimiento_id_categorizacion2_seq', 1, false);
          came          adm_came    false    206                       0    0    secuencia_id    SEQUENCE SET     :   SELECT pg_catalog.setval('came.secuencia_id', 168, true);
          came          adm_came    false    197            �      x������ � �      �      x������ � �         B   x��I�0�������#j��?���:� �1Z�ZEAٍQj��X�b<���F�>�o�� �x      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   2  x�}�=N�@Fk�nh����l�P� �IAc#b6r�r���J���m�����!�D�h	2(p��@��m��M�7�j���%H��C<:&��(��5oC���~)� � S�e!Z�Rqs?{���őI���h8�l��橮�ʯJcT�HPKd�PG )X�ј㤊;W04���ЮF\���´�y����]��D�p�O1@������y�}ݵ?*g�U���iJR���(���r����.������hރv���z����@�H�?�H��#).F�D^ ���ҿ\z��,�/��0      �      x������ � �      �      x������ � �      �      x������ � �      �   5   x�32143�4��0��4661766��HL���,IL��26���CR���� T^�      �      x������ � �      �   k   x�M���0 ��LP��N�]����@��$���S�;#�q6M�(kK��m+��3K64�pն�9��O,��P(��}Z}j�>E��Q�;��ҽ�6�~�u�#�      �      x������ � �      �      x������ � �      �   �   x���M�0��5��h�i�&�4�nLLI)&x'W��	���1n'y�oX��L�2J�,,���
���<�8O�m�iԃ���=�/���h%���`

k.+�Z2���ܞ��=�q�,Q���}�{x��,�鋟O~�jh՛ɐ	I&J�u0P��$ٙj� ��QD���`�"#����������o      �      x������ � �      �      x������ � �      �      x������ � �      �   !  x����J�0�s�y�-�d�Lzً �z�R��XIw��<��b�.��*r���_� ���$e@A%�I�R��R�3Ba��c���i㇐b�c�!���.f�
8�+����!86F�Jw5. B���J�F�����������_�`8&��rb1�O��p��ͺ��z���:No�R� I�$�y��!�P�cZ/�1�<�^].M� 4�px�����O�C�5i������ð��{�T_�L�T+L#��Aŭ2�������������      �   s  x���[n�0E��Ud1���"��,��Gǉۂ&�� �Æ`�\j.�H%	��R�C�B�F&A�fba�_�T41�����B(P� ��w�w�7��ڝ󦬗�_��W�`@G�h¨3�`�N���Ψ� 󁝙�����/�x�4�j͈�l�� ,_au�(vw�}XL�Z�%�Y3��yg��<�4�r#�a��:���~c�A�e*A����ZA=�A��J�PS��l�I#���M��� >*Ȥ�@S�P�K2��)H��m�Wɱ�#��0�P��g��<Ἂ���uFJ0�ft��*cv
b� GjY;ܸyd<�.�T������Lj*X�����(O��s}�����C�u�X��!�T���~o�"�(0wP��#8���@��4=�)�@ʮ1*�G��'�@ʺ'Բ��+11�*���N�e݀����=~�R����ΰ��*��jV�����VՀn�����Y��j"��(�2��<�jk�g:�6�,2k"�����XL;sb��T�n�~�a>��`�3-oK�`Q/HY�Dw���5��P��s�ld;�d�P�vv�v?Ah�%�+�ҙ���M�z��L���'��F�	pvk�OŧA>n�����N      �   A   x�3214571241 �F�Ɯřy
��ɩ��\F�&�e�EX�MA�
�
�)�%�E\1z\\\ ���      �   @   x�32143�0112��0�06277��KL���K��24�,JM�%i�YP�_����	������ 7�O      �   �  x����u�0�Vn�|��Z�Yيό��`	w3Ae�W����pu�Mm�v< ���K�	9�N��6�o�[�Z��Kmc�tY^�?Ndu����S��r�nN��t�f{%��-��9j�B����h\����Cb�5+�ͩS}ٴ�8@�AME��.�8��4q���rH�j�����5�Ƹ��s���X��KL�� ��C�G5yJ-^�-#����L�m�V\��-߮��+L�lKv��%��9ŭ��67���%Y��'�m��b��~���)���`����+|bک�S�}s"%�5eI�.�C!gB�8�WCl�i��8c�R�o=<�ޝY�ls�G�����ɪ������������vD:S�N�r O=��B����g��.��           x��Uّ�@��(H W߇���?�'cs�c�K���=�Z�F�T��\�����bDF��"�!�3�Y�$<��0������e��t]~���糆k�5^����H�����d�)&�8E����XV���Dp�wN�Yz�H�8f�`��7��<���L��)g�=N��IՓFN�h��ձqQ#Ъnٛ�{N�	��Sx�6i�81�(WK��g��RN͑S��K��o>��AG(��53M)�5�\*��b�J'�&+1ܧ�k�8w8�f�I2P����%���o��^�Wr�}��!O�Գ���nRR��:�C�Ӗg���S�	Cٍ�Up	s�CD��g���"E�B�$(Rغ$V,Wש�!�1.���\����$t	�-cQǛr�8"i��7���D����&'Q�a���y�cq`Y����0���o�Ƃ��*�w̪���N|��4	?/���N�/�5�C9�y�$�tK|a^&��j���庽G'���������L���~�b�            x��U�m�`�S� ѽ����	$U	��S'���w��*��3k���I�Q���#�+�U�"<�`��n?O���.Sf�xz�9߿�5\�̍��bJl"Qǜ���3���a;N�&+rͤZ|�T��2�~Ω:KO����a&�:I��b��`L&�|�
�2)Õ�8�M�"�2WN#jZ�-_�	Nω0Y��S�;j<Y81�(WK��g�S���)%� jb�5N(�@����53M�6ʹ�7���9��
�+1��
�p�l�xr�+Ԍ���S��"�Z�Wr���>q�'Z��:�X��r�w��������W���r%�`�c�1���򗈺Pϔc_q�}+B��Y�݉E����A���2���wDH�b+<��Dd��!Xl/"<pE�j$�L�]�.��.� �:p#�������>�
�;"��q4��V�Ǽ�B����Ԡ����I�["�	��ph6q�Y���ኡ�MJH��E��$vq��	��]f���=��W�ݜ��mq����/�Hbt      �   �  x���KjA��=��\�[�ކlC �@0y���!v`�#�b���r;=�dp/�*����K%f�T��&N�lmDF��"���w���ןw_nf�n���������ٯ�G�Q�	kR��Z�G���.q�=GZ=G`�I��+�{�h%�b�n�7G�>R4S�֗�h�νD{��U��,%�8g}�n�7KW����
q	�H'ӎ_Ha;[J�d�v�n�7G�:JoJf|��,�ߥ{:T;�#f2���o���&���.��;1RF��Sl���wY軩�	:��O����o��ζ�C��\-m1w�Y��7'�e�9bx�^�]vt��@CF(/�b�7KW#U#-b��C�P�L(p�w�ؑ������o��hdk�!.�w�yUD��ߞ.�L�䂞�&~�t�h.����9{�v���M�+'˫��;į+�#�fޢ?0�G�3�����1e��K��@*�ϱ����-1�1X��2����D-̏	��<(!y,S,�Ⱦ� �/�Hr[Ҿٗ��҅��k�3�*P*(�6M;m�)��j��~7��n?��Dڞ�O��H�3�_��S�<|X�$��D16�J��p:�?�����L�|�'5��̖����G��Ħ�(M�d8� �N؝X�'�_�ݼ��}��v����ݟ�߯>�}��� �`o����˅����֠J��-�LgO�K"W�~@c|�RX��'��5��Udw���}����w����x�}�g���{.�L�a�^}l���/�>�      �   �  x���In�f���)|Q5��"ȦOЀa���i����(:����J��^�3U*'s.Q�ҕ�cDF��"��o_WBBk������!�k�)�W�=�����usAl��?//�"N�m�2$Yq�߻����J�@��1��&e1���>��q/����ط����H�j�� ��!V�¨#!���bh�[�Aq kBSdc�zW���5����B�k�h���fbKh��H�rj���Dp��͈�Q�-z+�Ll�s �gUs°����G4�9���n��Ll	-�CS�3�I�6���Us6a��;�&���Zm���f�	m����ǪA��%`�',,���fbhF[�A�3�КEӸ���Є5�+��n����f�����<�9{�&
����ǴZ9YΫ���Tm< w��s`�3��2�B��W�d�i�:(�K3G_ˁf�%��H��Y�#�������C�2�35��b�\]��O
0:�٭�~�=�����������{�q����W@"4�~W�[l^��=��CXm����Pt8��>��2�����T5v�TΊ̌�O�N1FW��zs�\�Ql���$_����^n<N�zQ�	`.�N>��1�
,�p��2���b�Ax `"-�r
��a��P>���ީ��YE�SoR2�٢����#`^&��h��c]���)n���]p�H�m�������           x���ّ�@D��〈����-!�lh�W��eee��L���"Z$Hj�G�Ȩ�TDu�2]ſ�"<k�����{+��S[\攰����}�������4=kJl�Q�`�	�}���8HȢ���)R���Dh��������C�����ab$�ӳ�ib2�3�ϘhSf%���4�8MBV�5�Y�-��s&��>0���)��7&�r������bnQ92�*4%
oce:��DE(��Z��$F(��Pl�Ȗ<�	\��Jx���:A-l�h��8�E��"�����J.��i'�Ĺ���Y�� `�V�j�d��Y?�r%�ՐFv�p�c	s���r�+L̬��B��V���[�I�!���H=����Ԙ{Q�"<�vD��ɥ����������r�T���q�މ�OE�qh�g�E�7��e��u�YU ��t��[�0d�f�؉�w�Xvܝx��&\�#'���"��A��v5~}G_�qC6g��U�m�\. ��bb            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �      	      x������ � �      
      x������ � �         d  x���AN�0еs�� %�;˪	�*�+6&v������g�����C�b�Oc�����i�+Y���
Z�ʦ��Ɇ1.��w�6ES�B�V����&�1�GE3�aR�/���`�j�i�R�@+7Frm¶7�z<z�^�hq����ݛ͇��3*)6h�n����NU��+���:�y5���7���^�$���Z�����8�d����~������t+������t�uetҬ�Sj��O���;΂���s,��� ����y�de��Ȩ'���&�[�Ǚ�"W
+��j����ŗ��c�tn827�^*�)���Oܓ���'dp"��f�@�$��?
A�""�B.�,{�퍇         q   x�}���@��]�%�/�	<��Gd�p� ,<%ryV��T��َJ��5�4�x*m\�_��B���g�RmH����
˸=���B6���f��x��{��j%�            x������ � �            x������ � �         �  x�uR�j�0}� B���)˒���}ʋj���/A�����xכ�B[>g�h<B)��FI���x�(��ÔS��Ⱥ����@��υ+��6֡��S������#|!���Y�+V�0�Fǒ ��7�7�K�6�o�a�PXO|!�2Bh��x�5wg�ې����f��/��o3�'��p�~%�[���21��z��{m��B
{���2C�c= ��v3\n�:���)֩p!a�mӅԲj�����>@�r��G�u��5�qJ�gc��X�t؝�r�Ʒ@!�57��.EW沲��ơ��b���jm��ց5aJU��+-<�h�8�߱��#I��+�b��b�?�Q�Vh��@tze�9N��P�̅�����`{ �����z�����a��-�
�s֪�E��W�#         W  x����m1D�w�p��)�
\F�G������ �5�hH
�0�N�t��Fu�(����i+�@G�#�sH���膡�`�|�E�,x�<����U���=:n��A�uw���A�����v��Ȕ��-W�%�?1p�o���a�)*� ��?������a>X[�]bxU�yQJ�wn����]
Mf����i�K�,Q˹%�L�)��ޥr���n�SlT�c�Uɹ7�y.�5�mP��<�G괚条�xL-�7F�Wq��S��-�P��l�J�O��M��"���a�(jRӨ�?*���{#���3�k����S����u�۹ӽ     