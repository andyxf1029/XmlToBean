XmlToBean
=========

mapping tools for xml to bean in java 


sample 

InputDataSource source = new InputXMLDataSource(xml);// build xml to a inputDataSource  
<p></p>
TargetBean output = engine.process(source, TargetBean.class);

