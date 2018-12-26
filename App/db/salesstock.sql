CREATE DATABASE  IF NOT EXISTS `SalesStock` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `SalesStock`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: SalesStock
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BATCH_JOB_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_EXECUTION` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION`
--

LOCK TABLES `BATCH_JOB_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (1,2,1,'2018-09-19 21:44:14','2018-09-19 21:44:14','2018-09-19 21:44:15','FAILED','FAILED','','2018-09-19 21:44:15',NULL),(2,2,1,'2018-09-19 21:46:55','2018-09-19 21:46:55','2018-09-19 21:46:56','FAILED','FAILED','','2018-09-19 21:46:56',NULL),(3,2,1,'2018-09-19 21:49:22','2018-09-19 21:49:22','2018-09-19 21:49:23','COMPLETED','COMPLETED','','2018-09-19 21:49:23',NULL),(4,2,2,'2018-09-21 20:13:07','2018-09-21 20:13:08','2018-09-21 20:13:11','COMPLETED','COMPLETED','','2018-09-21 20:13:11',NULL),(5,2,3,'2018-09-21 20:25:36','2018-09-21 20:25:36','2018-09-21 20:25:37','COMPLETED','COMPLETED','','2018-09-21 20:25:37',NULL);
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_JOB_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (1,'{}',NULL),(2,'{}',NULL),(3,'{}',NULL),(4,'{}',NULL),(5,'{}',NULL);
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_PARAMS`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_PARAMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_PARAMS`
--

LOCK TABLES `BATCH_JOB_EXECUTION_PARAMS` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (1,'LONG','run.id','','1970-01-01 05:30:00',1,0,'Y'),(2,'LONG','run.id','','1970-01-01 05:30:00',1,0,'Y'),(3,'LONG','run.id','','1970-01-01 05:30:00',1,0,'Y'),(4,'LONG','run.id','','1970-01-01 05:30:00',2,0,'Y'),(5,'LONG','run.id','','1970-01-01 05:30:00',3,0,'Y');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_JOB_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_SEQ` VALUES (5,'0');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_INSTANCE`
--

DROP TABLE IF EXISTS `BATCH_JOB_INSTANCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_INSTANCE` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_INSTANCE`
--

LOCK TABLES `BATCH_JOB_INSTANCE` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (1,0,'importUserJob','853d3449e311f40366811cbefb3d93d7'),(2,0,'importUserJob','e070bff4379694c0210a51d9f6c6a564'),(3,0,'importUserJob','a3364faf893276dea0caacefbf618db5');
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_JOB_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_SEQ`
--

LOCK TABLES `BATCH_JOB_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_SEQ` VALUES (3,'0');
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION`
--

LOCK TABLES `BATCH_STEP_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (1,2,'step1',1,'2018-09-19 21:44:14','2018-09-19 21:44:14','FAILED',0,0,0,0,0,0,0,0,'FAILED','org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:149)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:310)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:197)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:66)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:67)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:136)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:308)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:141)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:134)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n	at java.lang.reflect.Method.invoke(Unknown Source)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:197)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy100.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner.execute(JobLauncherComm','2018-09-19 21:44:14'),(2,2,'step1',2,'2018-09-19 21:46:55','2018-09-19 21:46:56','FAILED',0,5,0,0,0,0,0,1,'FAILED','org.springframework.dao.DataIntegrityViolationException: PreparedStatementCallback; SQL [INSERT INTO people (first_name, last_name) VALUES (?, ?)Field \'person_id\' doesn\'t have a default value; nested exception is java.sql.BatchUpdateException: Field \'person_id\' doesn\'t have a default value\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:246)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)\r\n	at org.springframework.jdbc.core.JdbcTemplate.translateException(JdbcTemplate.java:1402)\r\n	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:620)\r\n	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:634)\r\n	at org.springframework.jdbc.core.JdbcTemplate.batchUpdate(JdbcTemplate.java:924)\r\n	at org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils.executeBatchUpdateWithNamedParameters(NamedParameterBatchUpdateUtils.java:43)\r\n	at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.batchUpdate(NamedParameterJdbcTemplate.java:355)\r\n	at org.springframework.batch.item.database.JdbcBatchItemWriter.write(JdbcBatchItemWriter.java:182)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:185)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:151)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:284)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:209)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:75)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:406)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:330)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:272)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:81)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.b','2018-09-19 21:46:56'),(3,3,'step1',3,'2018-09-19 21:49:22','2018-09-19 21:49:23','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-19 21:49:23'),(4,3,'step2',3,'2018-09-19 21:49:23','2018-09-19 21:49:23','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-19 21:49:23'),(5,3,'step1',4,'2018-09-21 20:13:09','2018-09-21 20:13:10','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-21 20:13:10'),(6,3,'step2',4,'2018-09-21 20:13:10','2018-09-21 20:13:11','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-21 20:13:11'),(7,3,'step1',5,'2018-09-21 20:25:36','2018-09-21 20:25:37','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-21 20:25:37'),(8,3,'step2',5,'2018-09-21 20:25:37','2018-09-21 20:25:37','COMPLETED',1,5,0,5,0,0,0,0,'COMPLETED','','2018-09-21 20:25:37');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_STEP_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (1,'{}',NULL),(2,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":0,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(3,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(4,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(5,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(6,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(7,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(8,'{\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"personItemReader.read.count\":6,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL);
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_STEP_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_SEQ` VALUES (8,'0');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BillInfo`
--

DROP TABLE IF EXISTS `BillInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BillInfo` (
  `InvoiceId` varchar(45) NOT NULL,
  `ProductId` varchar(45) NOT NULL,
  `SessionId` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `Unit` varchar(45) DEFAULT NULL,
  `UnitPrice` double DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `GrandTotal` double DEFAULT NULL,
  `Date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`InvoiceId`,`ProductId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BillInfo`
--

LOCK TABLES `BillInfo` WRITE;
/*!40000 ALTER TABLE `BillInfo` DISABLE KEYS */;
INSERT INTO `BillInfo` VALUES ('1536466014341','db287e4b-5e8b-4c00-b535-361fd66b83c8','D11BE91CF197ACC4B5983CBA878C8374','Nokia c3-Nokia','Piece',5000,1,5000,5110,'2018-09-09 04:07:05'),('1536466014341','e85290fe-0284-411c-b73f-3f385f30fbb6','D11BE91CF197ACC4B5983CBA878C8374','Facewash-Patanjali','Piece',50,1,50,5110,'2018-09-09 04:07:05'),('1536466014341','fcc37e08-34bd-461f-b156-a19126593c98','D11BE91CF197ACC4B5983CBA878C8374','Facewash-Himalaya','Piece',60,1,60,5110,'2018-09-09 04:07:05'),('1536466025910','db287e4b-5e8b-4c00-b535-361fd66b83c8','D11BE91CF197ACC4B5983CBA878C8374','Nokia c3-Nokia','Piece',5000,1,5000,5220,'2018-09-09 04:07:26'),('1536466025910','e85290fe-0284-411c-b73f-3f385f30fbb6','D11BE91CF197ACC4B5983CBA878C8374','Facewash-Patanjali','Piece',50,2,100,5220,'2018-09-09 04:07:26'),('1536466025910','fcc37e08-34bd-461f-b156-a19126593c98','D11BE91CF197ACC4B5983CBA878C8374','Facewash-Himalaya','Piece',60,2,120,5220,'2018-09-09 04:07:26'),('1536472350191','db287e4b-5e8b-4c00-b535-361fd66b83c8','EAFF0C0DA6BF78B0991CEF8D5CB02DB3','Nokia c3-Nokia','Piece',5000,2,10000,10050,'2018-09-09 05:52:32'),('1536472350191','e85290fe-0284-411c-b73f-3f385f30fbb6','EAFF0C0DA6BF78B0991CEF8D5CB02DB3','Facewash-Patanjali','Piece',50,1,50,10050,'2018-09-09 05:52:32'),('1536475146459','db287e4b-5e8b-4c00-b535-361fd66b83c8','F70CC183B8C36EAB04E0103B91352F41','Nokia c3-Nokia','Piece',5000,1,5000,5110,'2018-09-09 06:39:16'),('1536475146459','e85290fe-0284-411c-b73f-3f385f30fbb6','F70CC183B8C36EAB04E0103B91352F41','Facewash-Patanjali','Piece',50,1,50,5110,'2018-09-09 06:39:16'),('1536475146459','fcc37e08-34bd-461f-b156-a19126593c98','F70CC183B8C36EAB04E0103B91352F41','Facewash-Himalaya','Piece',60,1,60,5110,'2018-09-09 06:39:16'),('1536483013714','e85290fe-0284-411c-b73f-3f385f30fbb6','468B45A0AAE13EECCD210CEAC2F71657','Facewash-Patanjali','Piece',50,1,50,110,'2018-09-09 08:50:16'),('1536483013714','fcc37e08-34bd-461f-b156-a19126593c98','468B45A0AAE13EECCD210CEAC2F71657','Facewash-Himalaya','Piece',60,1,60,110,'2018-09-09 08:50:16'),('1536483021207','db287e4b-5e8b-4c00-b535-361fd66b83c8','468B45A0AAE13EECCD210CEAC2F71657','Nokia c3-Nokia','Piece',5000,1,5000,5110,'2018-09-09 08:50:23'),('1536483021207','e85290fe-0284-411c-b73f-3f385f30fbb6','468B45A0AAE13EECCD210CEAC2F71657','Facewash-Patanjali','Piece',50,1,50,5110,'2018-09-09 08:50:23'),('1536483021207','fcc37e08-34bd-461f-b156-a19126593c98','468B45A0AAE13EECCD210CEAC2F71657','Facewash-Himalaya','Piece',60,1,60,5110,'2018-09-09 08:50:23'),('1536483073489','e85290fe-0284-411c-b73f-3f385f30fbb6','0F00C8CFF069C5646C0543913BF7FB38','Facewash-Patanjali','Piece',50,1,50,50,'2018-09-09 08:51:15'),('1536483103982','db287e4b-5e8b-4c00-b535-361fd66b83c8','0F00C8CFF069C5646C0543913BF7FB38','Nokia c3-Nokia','Piece',5000,1,5000,5060,'2018-09-09 08:51:46'),('1536483103982','fcc37e08-34bd-461f-b156-a19126593c98','0F00C8CFF069C5646C0543913BF7FB38','Facewash-Himalaya','Piece',60,1,60,5060,'2018-09-09 08:51:46'),('1536502931923','e85290fe-0284-411c-b73f-3f385f30fbb6','39B5C67A9C989E13F66129AC19C801B7','Facewash-Patanjali','Piece',50,1,50,110,'2018-09-09 14:22:14'),('1536502931923','fcc37e08-34bd-461f-b156-a19126593c98','39B5C67A9C989E13F66129AC19C801B7','Facewash-Himalaya','Piece',60,1,60,110,'2018-09-09 14:22:14'),('1536502938350','db287e4b-5e8b-4c00-b535-361fd66b83c8','39B5C67A9C989E13F66129AC19C801B7','Nokia c3-Nokia','Piece',5000,1,5000,5110,'2018-09-09 14:22:21'),('1536502938350','e85290fe-0284-411c-b73f-3f385f30fbb6','39B5C67A9C989E13F66129AC19C801B7','Facewash-Patanjali','Piece',50,1,50,5110,'2018-09-09 14:22:21'),('1536502938350','fcc37e08-34bd-461f-b156-a19126593c98','39B5C67A9C989E13F66129AC19C801B7','Facewash-Himalaya','Piece',60,1,60,5110,'2018-09-09 14:22:21'),('1536503085175','e85290fe-0284-411c-b73f-3f385f30fbb6','1AD6E3D704E4D735094970000A5F8720','Facewash-Patanjali','Piece',50,1,50,110,'2018-09-09 14:24:47'),('1536503085175','fcc37e08-34bd-461f-b156-a19126593c98','1AD6E3D704E4D735094970000A5F8720','Facewash-Himalaya','Piece',60,1,60,110,'2018-09-09 14:24:47'),('1536503089664','db287e4b-5e8b-4c00-b535-361fd66b83c8','1AD6E3D704E4D735094970000A5F8720','Nokia c3-Nokia','Piece',5000,1,5000,5220,'2018-09-09 14:24:55'),('1536503089664','e85290fe-0284-411c-b73f-3f385f30fbb6','1AD6E3D704E4D735094970000A5F8720','Facewash-Patanjali','Piece',50,2,100,5220,'2018-09-09 14:24:55'),('1536503089664','fcc37e08-34bd-461f-b156-a19126593c98','1AD6E3D704E4D735094970000A5F8720','Facewash-Himalaya','Piece',60,2,120,5220,'2018-09-09 14:24:55'),('1536503169360','db287e4b-5e8b-4c00-b535-361fd66b83c8','B4C014DD676C43EFAC6DB08E47400B42','Nokia c3-Nokia','Piece',5000,1,5000,5110,'2018-09-09 14:26:12'),('1536503169360','e85290fe-0284-411c-b73f-3f385f30fbb6','B4C014DD676C43EFAC6DB08E47400B42','Facewash-Patanjali','Piece',50,1,50,5110,'2018-09-09 14:26:12'),('1536503169360','fcc37e08-34bd-461f-b156-a19126593c98','B4C014DD676C43EFAC6DB08E47400B42','Facewash-Himalaya','Piece',60,1,60,5110,'2018-09-09 14:26:12'),('1536503172586','db287e4b-5e8b-4c00-b535-361fd66b83c8','B4C014DD676C43EFAC6DB08E47400B42','Nokia c3-Nokia','Piece',5000,1,5000,5220,'2018-09-09 14:26:21'),('1536503172586','e85290fe-0284-411c-b73f-3f385f30fbb6','B4C014DD676C43EFAC6DB08E47400B42','Facewash-Patanjali','Piece',50,2,100,5220,'2018-09-09 14:26:21'),('1536503172586','fcc37e08-34bd-461f-b156-a19126593c98','B4C014DD676C43EFAC6DB08E47400B42','Facewash-Himalaya','Piece',60,2,120,5220,'2018-09-09 14:26:21'),('1536503685309','e85290fe-0284-411c-b73f-3f385f30fbb6','72DFD92857FCD2E003D0B3DF7DB28C63','Facewash-Patanjali','Piece',50,1,50,110,'2018-09-09 14:34:48'),('1536503685309','fcc37e08-34bd-461f-b156-a19126593c98','72DFD92857FCD2E003D0B3DF7DB28C63','Facewash-Himalaya','Piece',60,1,60,110,'2018-09-09 14:34:48'),('1536503734911','e85290fe-0284-411c-b73f-3f385f30fbb6','64A8A46E3AD3C47036277FCC78A8D8A2','Facewash-Patanjali','Piece',50,1,50,110,'2018-09-09 14:35:39'),('1536503734911','fcc37e08-34bd-461f-b156-a19126593c98','64A8A46E3AD3C47036277FCC78A8D8A2','Facewash-Himalaya','Piece',60,1,60,110,'2018-09-09 14:35:39'),('1536999461710','fcc37e08-34bd-461f-b156-a19126593c98','2EE41D3A5656F94074090380F1C72DD5','Facewash-Himalaya','Piece',60,1,60,60,'2018-09-15 08:17:49');
/*!40000 ALTER TABLE `BillInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Brands`
--

DROP TABLE IF EXISTS `Brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Brands` (
  `Id` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Brands`
--

LOCK TABLES `Brands` WRITE;
/*!40000 ALTER TABLE `Brands` DISABLE KEYS */;
INSERT INTO `Brands` VALUES ('de0cacee-b265-483c-b5ce-dc9526a77693','Patanjali','Patanjali Products',1),('e23f4043-1eec-497b-9ced-9b7441fe078b','Bajaj','Bajaj company',1),('26f2ad8d-1a2f-414f-abbc-53f07f0c7048','Himalaya','Himalaya Products',1),('29c79dfd-37c9-4d70-a622-4cb005db3e2d','Reebok','Reebok Desc',1),('b0fd8feb-dc9a-4305-8ff4-ff32b7e66442','Sandisk','Sandisk',1),('ec8674d1-7497-41d1-a5e5-5ed550a95c09','Nokia','Nokia',1);
/*!40000 ALTER TABLE `Brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Brands_ProductCategory`
--

DROP TABLE IF EXISTS `Brands_ProductCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Brands_ProductCategory` (
  `BrandId` varchar(45) NOT NULL,
  `ProductCategoryId` varchar(45) NOT NULL,
  PRIMARY KEY (`BrandId`,`ProductCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Brands_ProductCategory`
--

LOCK TABLES `Brands_ProductCategory` WRITE;
/*!40000 ALTER TABLE `Brands_ProductCategory` DISABLE KEYS */;
INSERT INTO `Brands_ProductCategory` VALUES ('26f2ad8d-1a2f-414f-abbc-53f07f0c7048','bff4e61a-0083-4b08-9289-c0ee44155634'),('29c79dfd-37c9-4d70-a622-4cb005db3e2d','fdef1a3f-7511-4d29-911f-099d8423cda1'),('b0fd8feb-dc9a-4305-8ff4-ff32b7e66442','a57a33b7-4c6a-4bae-81b7-c2d029716610'),('de0cacee-b265-483c-b5ce-dc9526a77693','bff4e61a-0083-4b08-9289-c0ee44155634'),('e23f4043-1eec-497b-9ced-9b7441fe078b','ba38e6a4-445f-48b5-ad6f-dbb8465b974f'),('ec8674d1-7497-41d1-a5e5-5ed550a95c09','a57a33b7-4c6a-4bae-81b7-c2d029716610');
/*!40000 ALTER TABLE `Brands_ProductCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductCategory`
--

DROP TABLE IF EXISTS `ProductCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductCategory` (
  `Id` varchar(45) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductCategory`
--

LOCK TABLES `ProductCategory` WRITE;
/*!40000 ALTER TABLE `ProductCategory` DISABLE KEYS */;
INSERT INTO `ProductCategory` VALUES ('a57a33b7-4c6a-4bae-81b7-c2d029716610','Mobiles,Computers and Accessories','Mobiles,Computers and Accessories',1),('ba38e6a4-445f-48b5-ad6f-dbb8465b974f','TV,Appliances,Electronics','TV,Appliances,Electronics',1),('bff4e61a-0083-4b08-9289-c0ee44155634','Beauty, Health,Grocery','Beauty, Health,Grocery',1),('fdef1a3f-7511-4d29-911f-099d8423cda1','Sports,Fitness Bag, Luggage','Sports,Fitness Bag, Luggage',1);
/*!40000 ALTER TABLE `ProductCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductQuotation`
--

DROP TABLE IF EXISTS `ProductQuotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductQuotation` (
  `Id` varchar(45) NOT NULL,
  `Quantity` int(10) DEFAULT NULL,
  `QuantityUnitId` varchar(45) DEFAULT NULL,
  `PricePerUnit` double DEFAULT NULL,
  `Discount` double DEFAULT NULL,
  `VendorId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `ii_idx` (`QuantityUnitId`),
  CONSTRAINT `ii` FOREIGN KEY (`QuantityUnitId`) REFERENCES `QuantityUnit` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductQuotation`
--

LOCK TABLES `ProductQuotation` WRITE;
/*!40000 ALTER TABLE `ProductQuotation` DISABLE KEYS */;
INSERT INTO `ProductQuotation` VALUES ('271532ad-7f3b-4598-8dde-bd43c5f4bf4e',49,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',500,0,NULL),('43bd32a2-0d88-4a07-9aa5-f9457f4e4507',100,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',5000,0,NULL),('66a5ec1e-fc87-4e70-a794-04bdcd8be4b5',100,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',1200,0,NULL),('767b4da5-19c1-4dad-b951-b08af57949a0',0,'3deb2d66-549c-4557-a4ea-7a4371c561e7',0,0,NULL),('8bdc61c3-00dd-4007-ac18-3f212c29a37a',0,'7092b05a-089f-4238-8ee7-5d31d7e0abc9',0,0,NULL),('a17c2549-8496-436a-861e-50c858deef42',50,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',1000,0,NULL),('be2c635e-faa8-4824-a5e8-0fce1842a3ee',93,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',50,0,NULL),('d609eeac-ed79-4b02-923d-a8f118beacec',32,'c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8',60,0,NULL);
/*!40000 ALTER TABLE `ProductQuotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductSubType`
--

DROP TABLE IF EXISTS `ProductSubType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductSubType` (
  `Id` varchar(45) NOT NULL,
  `ProductTypeId` varchar(45) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk1_idx` (`ProductTypeId`),
  CONSTRAINT `aa` FOREIGN KEY (`ProductTypeId`) REFERENCES `ProductType` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductSubType`
--

LOCK TABLES `ProductSubType` WRITE;
/*!40000 ALTER TABLE `ProductSubType` DISABLE KEYS */;
INSERT INTO `ProductSubType` VALUES ('01d0a103-f804-4fff-a31c-102aab0a81bb','602980ce-cefc-468e-8125-46878ee7eafb','Laptop','Laptop Desc',1),('6da7df25-b81c-4a58-8173-0db946a36619','49ca4fcb-9b82-49f9-a263-1709b4d21172','Light','Light Desc',1),('a380b04d-8e6d-4cfc-aa9c-3c390b81536b','602980ce-cefc-468e-8125-46878ee7eafb','Desktop','Desktop Desc',1),('aa992ff3-1a68-4579-86ff-bb1aa2c8173e','49ca4fcb-9b82-49f9-a263-1709b4d21172','Fan','Fan Desc',1);
/*!40000 ALTER TABLE `ProductSubType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductType`
--

DROP TABLE IF EXISTS `ProductType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductType` (
  `Id` varchar(45) NOT NULL,
  `ProductCategoryId` varchar(45) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk1_idx` (`ProductCategoryId`),
  CONSTRAINT `pcidi` FOREIGN KEY (`ProductCategoryId`) REFERENCES `ProductCategory` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductType`
--

LOCK TABLES `ProductType` WRITE;
/*!40000 ALTER TABLE `ProductType` DISABLE KEYS */;
INSERT INTO `ProductType` VALUES ('49ca4fcb-9b82-49f9-a263-1709b4d21172','ba38e6a4-445f-48b5-ad6f-dbb8465b974f','Electronics','Electronics',1),('602980ce-cefc-468e-8125-46878ee7eafb','a57a33b7-4c6a-4bae-81b7-c2d029716610','Computers','Computers and Accessories',1),('73ad4ee6-ba2d-4c6a-b448-141d44896d6d','a57a33b7-4c6a-4bae-81b7-c2d029716610','Mobile','Mobile Phones',1),('846c0635-9afd-40b5-b3a1-44c7fd595dec','ba38e6a4-445f-48b5-ad6f-dbb8465b974f','TV','Television',1),('85d727e2-b1db-44f2-858b-301add27b467','bff4e61a-0083-4b08-9289-c0ee44155634','Beauty ','Beauty and Cosmetics',1),('c7b2a34f-6933-4b46-991d-d503a2b47c89','ba38e6a4-445f-48b5-ad6f-dbb8465b974f','Appliances','Household Appliances',1),('db9d382b-d6a2-4ca7-b411-7e3c4b163bb4','fdef1a3f-7511-4d29-911f-099d8423cda1','Sports','Sports',1),('f5ba33a3-09e9-4c20-a4e0-72fe96613ad3','bff4e61a-0083-4b08-9289-c0ee44155634','Grocery','Grocery Description',1);
/*!40000 ALTER TABLE `ProductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `Id` varchar(45) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) DEFAULT NULL,
  `CreatedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ProductSubTypeId` varchar(45) DEFAULT NULL,
  `ProductTypeId` varchar(45) NOT NULL,
  `ProductQuotationId` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `pp_idx` (`ProductSubTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Products`
--

LOCK TABLES `Products` WRITE;
/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES ('9a27f405-ed36-4d5f-97ab-a05e784417b7','Shoes','Sports shows',1,'2018-08-14 14:03:54','-1','db9d382b-d6a2-4ca7-b411-7e3c4b163bb4','a17c2549-8496-436a-861e-50c858deef42'),('b0960499-3d76-47ed-8b76-731672672bfa','Mixer Grinder','500 WT',1,'2018-08-15 14:00:18','-1','c7b2a34f-6933-4b46-991d-d503a2b47c89','66a5ec1e-fc87-4e70-a794-04bdcd8be4b5'),('b17c4a15-4abf-47c8-b822-ca365b9a1aac','Pendrive','16 gb 2.0 flash',1,'2018-08-13 17:55:04','-1','602980ce-cefc-468e-8125-46878ee7eafb','271532ad-7f3b-4598-8dde-bd43c5f4bf4e'),('db287e4b-5e8b-4c00-b535-361fd66b83c8','Nokia c3','2MP, 100MB',1,'2018-08-13 17:42:23','-1','73ad4ee6-ba2d-4c6a-b448-141d44896d6d','43bd32a2-0d88-4a07-9aa5-f9457f4e4507'),('e85290fe-0284-411c-b73f-3f385f30fbb6','Facewash','Neem facewash',1,'2018-08-13 17:45:40','-1','85d727e2-b1db-44f2-858b-301add27b467','be2c635e-faa8-4824-a5e8-0fce1842a3ee'),('fcc37e08-34bd-461f-b156-a19126593c98','Facewash','Neem facewash',1,'2018-08-13 17:46:18','-1','85d727e2-b1db-44f2-858b-301add27b467','d609eeac-ed79-4b02-923d-a8f118beacec');
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Products_Brands`
--

DROP TABLE IF EXISTS `Products_Brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products_Brands` (
  `ProductId` varchar(45) NOT NULL,
  `BrandId` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Products_Brands`
--

LOCK TABLES `Products_Brands` WRITE;
/*!40000 ALTER TABLE `Products_Brands` DISABLE KEYS */;
INSERT INTO `Products_Brands` VALUES ('fcc37e08-34bd-461f-b156-a19126593c98','26f2ad8d-1a2f-414f-abbc-53f07f0c7048'),('b17c4a15-4abf-47c8-b822-ca365b9a1aac','b0fd8feb-dc9a-4305-8ff4-ff32b7e66442'),('9a27f405-ed36-4d5f-97ab-a05e784417b7','29c79dfd-37c9-4d70-a622-4cb005db3e2d'),('b0960499-3d76-47ed-8b76-731672672bfa','e23f4043-1eec-497b-9ced-9b7441fe078b'),('db287e4b-5e8b-4c00-b535-361fd66b83c8','ec8674d1-7497-41d1-a5e5-5ed550a95c09'),('e85290fe-0284-411c-b73f-3f385f30fbb6','de0cacee-b265-483c-b5ce-dc9526a77693');
/*!40000 ALTER TABLE `Products_Brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuantityUnit`
--

DROP TABLE IF EXISTS `QuantityUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuantityUnit` (
  `Id` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuantityUnit`
--

LOCK TABLES `QuantityUnit` WRITE;
/*!40000 ALTER TABLE `QuantityUnit` DISABLE KEYS */;
INSERT INTO `QuantityUnit` VALUES ('3deb2d66-549c-4557-a4ea-7a4371c561e7','liter','Liter',1),('7092b05a-089f-4238-8ee7-5d31d7e0abc9','KG','Kilograms',1),('c03b1e7c-ec23-499e-8a68-ad6fc83a1fa8','Piece','Pieces',1);
/*!40000 ALTER TABLE `QuantityUnit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `person_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'JILLP','DOE'),(2,'JOEP','DOE'),(3,'JUSTINP','DOE'),(4,'JANEP','DOE'),(5,'JOHNP','DOE'),(6,'Jill','Doe'),(7,'Joe','Doe'),(8,'Justin','Doe'),(9,'Jane','Doe'),(10,'John','Doe'),(11,'JILLP','DOE'),(12,'JOEP','DOE'),(13,'JUSTINP','DOE'),(14,'JANEP','DOE'),(15,'JOHNP','DOE'),(16,'Jill','Doe'),(17,'Joe','Doe'),(18,'Justin','Doe'),(19,'Jane','Doe'),(20,'John','Doe'),(21,'JILLP','DOE'),(22,'JOEP','DOE'),(23,'JUSTINP','DOE'),(24,'JANEP','DOE'),(25,'JOHNP','DOE'),(26,'Jill','Doe'),(27,'Joe','Doe'),(28,'Justin','Doe'),(29,'Jane','Doe'),(30,'John','Doe');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `productsearchview`
--

DROP TABLE IF EXISTS `productsearchview`;
/*!50001 DROP VIEW IF EXISTS `productsearchview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `productsearchview` (
  `ProductId` tinyint NOT NULL,
  `ProductName` tinyint NOT NULL,
  `ProductDesc` tinyint NOT NULL,
  `ProductStatusId` tinyint NOT NULL,
  `ProductStatus` tinyint NOT NULL,
  `PCName` tinyint NOT NULL,
  `ProductCategoryId` tinyint NOT NULL,
  `ProductTypeId` tinyint NOT NULL,
  `PTName` tinyint NOT NULL,
  `PTDesc` tinyint NOT NULL,
  `ProductSubTypeId` tinyint NOT NULL,
  `PSTName` tinyint NOT NULL,
  `PSTDesc` tinyint NOT NULL,
  `Quantity` tinyint NOT NULL,
  `PricePerUnit` tinyint NOT NULL,
  `QName` tinyint NOT NULL,
  `BrandId` tinyint NOT NULL,
  `BrandName` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `productsview`
--

DROP TABLE IF EXISTS `productsview`;
/*!50001 DROP VIEW IF EXISTS `productsview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `productsview` (
  `ProductId` tinyint NOT NULL,
  `ProductName` tinyint NOT NULL,
  `ProductDesc` tinyint NOT NULL,
  `ProductStatus` tinyint NOT NULL,
  `PTName` tinyint NOT NULL,
  `PTDesc` tinyint NOT NULL,
  `PSTName` tinyint NOT NULL,
  `PSTDesc` tinyint NOT NULL,
  `Quantity` tinyint NOT NULL,
  `PricePerUnit` tinyint NOT NULL,
  `QName` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'SalesStock'
--

--
-- Final view structure for view `productsearchview`
--

/*!50001 DROP TABLE IF EXISTS `productsearchview`*/;
/*!50001 DROP VIEW IF EXISTS `productsearchview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `productsearchview` AS select `p`.`Id` AS `ProductId`,`p`.`Name` AS `ProductName`,`p`.`Description` AS `ProductDesc`,`p`.`Status` AS `ProductStatusId`,if((`p`.`Status` = 1),'Avail','Not-Avail') AS `ProductStatus`,`pc`.`Name` AS `PCName`,`pc`.`Id` AS `ProductCategoryId`,`pt`.`Id` AS `ProductTypeId`,`pt`.`Name` AS `PTName`,`pt`.`Description` AS `PTDesc`,`pst`.`Id` AS `ProductSubTypeId`,`pst`.`Name` AS `PSTName`,`pst`.`Description` AS `PSTDesc`,`pq`.`Quantity` AS `Quantity`,`pq`.`PricePerUnit` AS `PricePerUnit`,`qu`.`Name` AS `QName`,`b`.`Id` AS `BrandId`,`b`.`Name` AS `BrandName` from (((((((`products` `p` join `producttype` `pt` on((`p`.`ProductTypeId` = `pt`.`Id`))) left join `productsubtype` `pst` on(((`p`.`ProductSubTypeId` = `pst`.`Id`) and (`pt`.`Id` = `pst`.`ProductTypeId`)))) left join `productcategory` `pc` on((`pt`.`ProductCategoryId` = `pc`.`Id`))) left join `productquotation` `pq` on((`p`.`ProductQuotationId` = `pq`.`Id`))) left join `quantityunit` `qu` on((`pq`.`QuantityUnitId` = `qu`.`Id`))) left join `products_brands` `pb` on((`p`.`Id` = `pb`.`ProductId`))) left join `brands` `b` on(((`b`.`Id` = `pb`.`BrandId`) and (`b`.`Status` = 1)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `productsview`
--

/*!50001 DROP TABLE IF EXISTS `productsview`*/;
/*!50001 DROP VIEW IF EXISTS `productsview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `productsview` AS select `p`.`Id` AS `ProductId`,`p`.`Name` AS `ProductName`,`p`.`Description` AS `ProductDesc`,if((`p`.`Status` = 1),'Avail','Not-Avail') AS `ProductStatus`,`pt`.`Name` AS `PTName`,`pt`.`Description` AS `PTDesc`,`pst`.`Name` AS `PSTName`,`pst`.`Description` AS `PSTDesc`,`pq`.`Quantity` AS `Quantity`,`pq`.`PricePerUnit` AS `PricePerUnit`,`qu`.`Name` AS `QName` from (((((`products` `p` join `producttype` `pt` on((`p`.`ProductTypeId` = `pt`.`Id`))) left join `productsubtype` `pst` on(((`p`.`ProductSubTypeId` = `pst`.`Id`) and (`pt`.`Id` = `pst`.`ProductTypeId`)))) left join `productcategory` `pc` on((`pt`.`ProductCategoryId` = `pc`.`Id`))) left join `productquotation` `pq` on((`p`.`ProductQuotationId` = `pq`.`Id`))) left join `quantityunit` `qu` on((`pq`.`QuantityUnitId` = `qu`.`Id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-01 20:55:19
